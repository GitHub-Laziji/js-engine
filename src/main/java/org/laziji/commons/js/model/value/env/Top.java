package org.laziji.commons.js.model.value.env;

import org.laziji.commons.js.exception.CompileException;
import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.manager.NodeConfiguration;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.DocNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.*;
import org.laziji.commons.js.util.TokenUtils;

import java.util.*;

public class Top {

    private static ThreadLocal<ThreadLocalTop> local = new ThreadLocal<>();

    public static void setThreadLocalTop(ThreadLocalTop threadLocalTop) {
        local.set(threadLocalTop);
    }

    public static void addInternalModules(String name, ModuleValue module) {
        getThreadLocalTop().getInternalModules().put(name, module);
    }

    public static void addMacroTask(Runner runner) {
        synchronized (getThreadLocalTop()) {
            getThreadLocalTop().getMacroTasks().add(runner);
            getThreadLocalTop().notifyAll();
        }
    }

    public static void addDelayMacroTaskId(String id) {
        synchronized (getThreadLocalTop()) {
            getThreadLocalTop().getDelayMacroTaskIds().add(id);
        }
    }

    public static void deleteDelayMacroTaskId(String id) {
        synchronized (getThreadLocalTop()) {
            getThreadLocalTop().getDelayMacroTaskIds().remove(id);
        }
    }

    public static void addMicroTask(Runner runner) {
        synchronized (getThreadLocalTop()) {
            getThreadLocalTop().getMicroTasks().add(runner);
        }
    }

    public static void eval(String text) throws Exception {
        eval(compile(text));
    }

    public static void eval(DocNode doc) {
        addMacroTask(() -> doc.run(getThreadLocalTop().getMainContexts()));
    }

    public static DocNode compile(String text) throws Exception {
        NodeConfiguration configuration = new NodeConfiguration();
        configuration.setStrict(getThreadLocalTop().isStrict());
        DocNode doc = new DocNode(configuration);
        List<Node.TokenUnit> tokens = TokenUtils.parseTextToTokens(text);
        Node p = doc;
        for (Node.TokenUnit token : tokens) {
//            System.out.println(JSON.toJSONString(token) + " " + p.getSelf().getClass().getSimpleName());
            p = p.append(token);
        }
        if (!doc.isDone()) {
            throw new CompileException();
        }
        return doc;
    }

    public static void loop() throws Exception {
        while (true) {
            synchronized (getThreadLocalTop()) {
                Queue<Runner> macroTasks = getThreadLocalTop().getMacroTasks();
                Queue<Runner> microTasks = getThreadLocalTop().getMicroTasks();
                Set<String> delayMacroTaskIds = getThreadLocalTop().getDelayMacroTaskIds();
                while (!macroTasks.isEmpty()) {
                    macroTasks.poll().run();
                }
                while (!microTasks.isEmpty()) {
                    microTasks.poll().run();
                }
                if (delayMacroTaskIds.isEmpty()) {
                    break;
                }
                getThreadLocalTop().wait();
            }
        }
    }

    public static ModuleValue getModule(String name) throws RunException {
        Map<String, ModuleValue> internalModules = getThreadLocalTop().getInternalModules();
        if (internalModules.containsKey(name)) {
            return internalModules.get(name);
        }
        // TODO
        throw new RunException("Module not find");
    }


    public static ObjectValue getGlobal() {
        return getThreadLocalTop().getGlobal();
    }

    public static ObjectClass getObjectClass() {
        return getThreadLocalTop().getObjectClass();
    }

    public static FunctionClass getFunctionClass() {
        return getThreadLocalTop().getFunctionClass();
    }

    public static StringClass getStringClass() {
        return getThreadLocalTop().getStringClass();
    }

    public static NumberClass getNumberClass() {
        return getThreadLocalTop().getNumberClass();
    }

    public static BooleanClass getBooleanClass() {
        return getThreadLocalTop().getBooleanClass();
    }

    public static void setStrict(boolean strict) {
        getThreadLocalTop().setStrict(strict);
    }

    public static Contexts getMainContexts() {
        return getThreadLocalTop().getMainContexts();
    }

    private static ThreadLocalTop getThreadLocalTop() {
        ThreadLocalTop threadLocalTop = local.get();
        if (threadLocalTop == null) {
            threadLocalTop = new ThreadLocalTop();
            local.set(threadLocalTop);
        }
        return threadLocalTop;
    }

    @FunctionalInterface
    public interface Runner {

        void run() throws Exception;
    }
}
