package org.laziji.commons.js.model.value;

import org.laziji.commons.js.exception.CompileException;
import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.manager.NodeConfiguration;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.DocNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.util.TokenUtils;

import java.util.*;

public class Top {

    private static Queue<Runner> macroTasks = new LinkedList<>();
    private static Set<String> delayMacroTaskIds = new HashSet<>();
    private static Queue<Runner> microTasks = new LinkedList<>();
    private static Map<String, ModuleValue> internalModules = new HashMap<>();
    private static Map<String, ModuleValue> externalModules = new HashMap<>();
    private static boolean strict;

    private static Contexts mainContexts = new Contexts();

    private static ObjectValue global = new GlobalObjectValue();

    private static ObjectClass objectClass = new ObjectClass();
    private static ObjectValue objectPrototype = new ObjectPrototype();

    private static FunctionClass functionClass = new FunctionClass();
    private static FunctionValue functionPrototype = new FunctionPrototype();

    private static StringClass stringClass = new StringClass();
    private static StringValue stringPrototype = new StringPrototype();

    private static NumberClass numberClass = new NumberClass();
    private static NumberValue numberPrototype = new NumberPrototype();

    private static BooleanClass booleanClass = new BooleanClass();
    private static BooleanValue booleanPrototype = new BooleanPrototype();

    static {

    }


    public static void addInternalModules(String name, ModuleValue module) {
        internalModules.put(name, module);
    }

    public static synchronized void addMacroTask(Runner runner) {
        macroTasks.add(runner);
        Top.class.notifyAll();
    }

    public static synchronized void addDelayMacroTaskId(String id) {
        delayMacroTaskIds.add(id);
    }

    public static synchronized void deleteDelayMacroTaskId(String id) {
        delayMacroTaskIds.remove(id);
    }

    public static synchronized void addMicroTask(Runner runner) {
        microTasks.add(runner);
    }

    public static void eval(String text) throws Exception {
        eval(compile(text));
    }

    public static void eval(DocNode doc) {
        addMacroTask(() -> doc.run(mainContexts));
    }

    public static DocNode compile(String text) throws Exception {
        NodeConfiguration configuration = new NodeConfiguration();
        configuration.setStrict(strict);
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
            synchronized (Top.class) {
                while (!macroTasks.isEmpty()) {
                    macroTasks.poll().run();
                }
                while (!microTasks.isEmpty()) {
                    microTasks.poll().run();
                }
                if (delayMacroTaskIds.isEmpty()) {
                    break;
                }
                Top.class.wait();
            }
        }
    }

    public static ModuleValue getModule(String name) throws RunException {
        if (internalModules.containsKey(name)) {
            return internalModules.get(name);
        }
        // TODO
        throw new RunException("Module not find");
    }


    public static ObjectValue getGlobal() {
        return global;
    }

    public static ObjectClass getObjectClass() {
        return objectClass;
    }

    public static FunctionClass getFunctionClass() {
        return functionClass;
    }

    public static StringClass getStringClass() {
        return stringClass;
    }

    public static NumberClass getNumberClass() {
        return numberClass;
    }

    public static BooleanClass getBooleanClass() {
        return booleanClass;
    }

    public static ObjectValue getObjectPrototype() {
        return objectPrototype;
    }

    public static StringValue getStringPrototype() {
        return stringPrototype;
    }

    public static FunctionValue getFunctionPrototype() {
        return functionPrototype;
    }

    public static NumberValue getNumberPrototype() {
        return numberPrototype;
    }

    public static BooleanValue getBooleanPrototype() {
        return booleanPrototype;
    }

    public static void setStrict(boolean strict) {
        Top.strict = strict;
    }

    public static Contexts getMainContexts() {
        return mainContexts;
    }

    @FunctionalInterface
    public interface Runner {

        void run() throws Exception;
    }
}
