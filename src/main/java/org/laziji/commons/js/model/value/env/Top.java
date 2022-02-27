package org.laziji.commons.js.model.value.env;

import org.laziji.commons.js.exception.CompileException;
import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.manager.NodeConfiguration;
import org.laziji.commons.js.model.node.DocNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.module.ModuleValue;
import org.laziji.commons.js.util.TokenUtils;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Top {

    private static final ThreadLocal<ThreadLocalTop> local = new ThreadLocal<>();

    private static final boolean DEBUG = true;

    public static void init(ThreadLocalTop threadLocalTop) {
        local.set(threadLocalTop);
    }

    public static void init(){
        init(new ThreadLocalTop());
    }

    public static void addInternalModules(String name, ModuleValue module) {
        local.get().getInternalModules().put(name, module);
    }

    public static void addMacroTask(Runner runner) {
        synchronized (local.get()) {
            local.get().getMacroTasks().add(runner);
            local.get().notifyAll();
        }
    }

    public static void addDelayMacroTaskId(String id) {
        synchronized (local.get()) {
            local.get().getDelayMacroTaskIds().add(id);
        }
    }

    public static void deleteDelayMacroTaskId(String id) {
        synchronized (local.get()) {
            local.get().getDelayMacroTaskIds().remove(id);
        }
    }

    public static void addMicroTask(Runner runner) {
        synchronized (local.get()) {
            local.get().getMicroTasks().add(runner);
        }
    }

    public static void eval(String text) throws Exception {
        eval(compile(text));
    }

    public static void eval(DocNode doc) {
        addMacroTask(() -> doc.run(local.get().getMainContexts()));
    }

    public static DocNode compile(String text) throws Exception {
        NodeConfiguration configuration = new NodeConfiguration();
        configuration.setStrict(local.get().isStrict());
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
        if (DEBUG) {
            System.out.println("=== DEBUG PRINT START =========");
            System.out.println(doc);
            System.out.println("=== DEBUG PRINT END   =========\n\n\n");
        }
        return doc;
    }

    public static void loop() throws Exception {
        while (true) {
            synchronized (local.get()) {
                Queue<Runner> macroTasks = local.get().getMacroTasks();
                Queue<Runner> microTasks = local.get().getMicroTasks();
                Set<String> delayMacroTaskIds = local.get().getDelayMacroTaskIds();
                while (!macroTasks.isEmpty()) {
                    macroTasks.poll().run();
                }
                while (!microTasks.isEmpty()) {
                    microTasks.poll().run();
                }
                if (delayMacroTaskIds.isEmpty()) {
                    break;
                }
                local.get().wait();
            }
        }
    }

    public static ModuleValue getModule(String name) throws RunException {
        Map<String, ModuleValue> internalModules = local.get().getInternalModules();
        if (internalModules.containsKey(name)) {
            return internalModules.get(name);
        }
        // TODO
        throw new RunException("Module not find");
    }

    public static ThreadLocalTop getThreadLocalTop() {
        return local.get();
    }

    @FunctionalInterface
    public interface Runner {

        void run() throws Exception;
    }
}
