package org.laziji.commons.js.model.value.module;

import org.laziji.commons.js.model.value.BaseJsValue;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.primitive.JsBoolean;
import org.laziji.commons.js.model.value.primitive.JsNumber;
import org.laziji.commons.js.model.value.primitive.JsString;

import java.util.HashMap;
import java.util.Map;

public class ModuleValue extends BaseJsValue {

    private JsValue defaultExportValue;
    private final Map<String, JsValue> export = new HashMap<>();

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

    @Override
    public JsBoolean toJsBoolean() {
        return JsBoolean.getTrueInstance();
    }

    @Override
    public JsNumber toJsNumber() {
        return new JsNumber(0);
    }

    @Override
    public JsString toJsString() {
        return new JsString("[module]");
    }
}
