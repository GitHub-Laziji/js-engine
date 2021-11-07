package org.laziji.commons.js.model.value.primitive;

import org.laziji.commons.js.model.value.JsObject;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;

public class JsString extends JsObject {

    private String value;

    public JsString(String value) {
        this.value = value;
    }

    @Override
    public JsValue addProperty(String key, JsValue value, ObjectPropertyType type) {
        return value;
    }

    @Override
    public JsValue getProto() {
        return Top.getStringClass().getPrototype();
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
