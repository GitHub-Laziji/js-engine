package org.laziji.commons.js.model.value.env;

import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.value.*;
import org.laziji.commons.js.model.value.module.ModuleValue;

import java.util.*;

public class ThreadLocalTop {

    private Queue<Top.Runner> macroTasks = new LinkedList<>();
    private Set<String> delayMacroTaskIds = new HashSet<>();
    private Queue<Top.Runner> microTasks = new LinkedList<>();
    private Map<String, ModuleValue> internalModules = new HashMap<>();
    private Map<String, ModuleValue> externalModules = new HashMap<>();
    private boolean strict;

    private Contexts mainContexts = new Contexts();

    private JsObject global = new JsGlobalObject();

    private ObjectClass objectClass = new ObjectClass();
    private FunctionClass functionClass = new FunctionClass();
    private StringClass stringClass = new StringClass();
    private NumberClass numberClass = new NumberClass();
    private BooleanClass booleanClass = new BooleanClass();

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

    public boolean isStrict() {
        return strict;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
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
}
