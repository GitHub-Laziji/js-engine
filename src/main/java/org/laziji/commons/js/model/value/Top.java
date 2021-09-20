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
    private static ObjectClass objectClass;
    private static ObjectPrototype objectPrototype;
    private static FunctionClass functionClass;
    private static StringClass stringClass;
    private static StringPrototype stringPrototype;
    private static NumberClass numberClass;
    private static BooleanClass booleanClass;

    static {
        objectClass = new ObjectClass();
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

    public static ObjectPrototype getObjectPrototype() {
        return objectPrototype;
    }

    public static StringPrototype getStringPrototype() {
        return stringPrototype;
    }
}
