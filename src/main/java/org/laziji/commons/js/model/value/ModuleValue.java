package org.laziji.commons.js.model.value;

import java.util.HashMap;
import java.util.Map;

public class ModuleValue extends JsObject {

    private JsValue defaultExportValue;
    private Map<String, JsValue> export = new HashMap<>();

    public JsValue getDefaultExportValue() {
        return defaultExportValue;
    }

    public void setDefaultExportValue(JsValue defaultExportValue) {
        this.defaultExportValue = defaultExportValue;
    }


    public void addExportValue(String name, JsValue value) {
        export.put(name, value);
    }

    public JsValue getExportValue(String name) {
        return export.get(name);
    }

}
