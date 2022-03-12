package org.laziji.commons.js.model.value.env;

import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.value.clazz.*;
import org.laziji.commons.js.model.value.module.ModuleValue;
import org.laziji.commons.js.model.value.object.JsGlobalObject;
import org.laziji.commons.js.model.value.object.JsObject;

import java.util.*;

public class ThreadLocalTop {

    private final Queue<Top.Runner> macroTasks = new LinkedList<>();
    private final Set<String> delayMacroTaskIds = new HashSet<>();
    private final Queue<Top.Runner> microTasks = new LinkedList<>();
    private final Map<String, ModuleValue> internalModules = new HashMap<>();
    private final Map<String, ModuleValue> externalModules = new HashMap<>();

    private final ObjectClass objectClass = new ObjectClass();
    private final FunctionClass functionClass = new FunctionClass();
    private final StringClass stringClass = new StringClass();
    private final NumberClass numberClass = new NumberClass();
    private final BooleanClass booleanClass = new BooleanClass();

    private final PromiseClass promiseClass = new PromiseClass();

    private final Contexts mainContexts = new Contexts();
    private final JsObject global = new JsGlobalObject();

    private Long startTime;
    private Long overtime;

    public Queue<Top.Runner> getMacroTasks() {
        return macroTasks;
    }

    public Set<String> getDelayMacroTaskIds() {
        return delayMacroTaskIds;
    }

    public Queue<Top.Runner> getMicroTasks() {
        return microTasks;
    }

    public Map<String, ModuleValue> getInternalModules() {
        return internalModules;
    }

    public Map<String, ModuleValue> getExternalModules() {
        return externalModules;
    }

    public Contexts getMainContexts() {
        return mainContexts;
    }

    public JsObject getGlobal() {
        return global;
    }

    public ObjectClass getObjectClass() {
        return objectClass;
    }

    public FunctionClass getFunctionClass() {
        return functionClass;
    }

    public StringClass getStringClass() {
        return stringClass;
    }

    public NumberClass getNumberClass() {
        return numberClass;
    }

    public BooleanClass getBooleanClass() {
        return booleanClass;
    }

    public PromiseClass getPromiseClass() {
        return promiseClass;
    }

    public void initStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public void setOvertime(Long overtime) {
        this.overtime = overtime;
    }

    public boolean isTimeout() {
        if (overtime == null || startTime == null) {
            return false;
        }
        return startTime + overtime <= System.currentTimeMillis();
    }

}
