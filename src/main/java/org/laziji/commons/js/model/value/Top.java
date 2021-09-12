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
}
