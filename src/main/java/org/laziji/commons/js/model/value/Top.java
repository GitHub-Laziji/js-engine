package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.manager.ScriptManager;

import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Top {

    private static Queue<ScriptManager.Runner> macroTasks;
    private static Set<String> delayMacroTaskIds;
    private static Queue<ScriptManager.Runner> microTasks;
    private static Map<String, ModuleValue> internalModules;
    private static Map<String, ModuleValue> externalModules;
    private static boolean strict;

    private static ObjectValue global;

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
}
