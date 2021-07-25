package org.laziji.commons.js.model.value;

import java.util.HashMap;
import java.util.Map;

public class ModuleValue extends BaseValue {

    private Value defaultExportValue;
    private Map<String, Value> export = new HashMap<>();

    public Value getDefaultExportValue() {
        return defaultExportValue;
    }

    public void setDefaultExportValue(Value defaultExportValue) {
        this.defaultExportValue = defaultExportValue;
    }


    public void addExportValue(String name, Value value) {
        export.put(name, value);
    }

    public Value getExportValue(String name) {
        return export.get(name);
    }

}
