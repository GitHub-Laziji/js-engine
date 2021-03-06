package org.laziji.commons.js.model.value.primitive;

import org.laziji.commons.js.model.value.object.JsObject;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;

public class JsString extends JsObject {

    private final String value;

    public JsString(String value) {
        this.value = value;
    }

    @Override
    public JsValue addProperty(String key, JsValue value, ObjectPropertyType type) {
        return value;
    }

    @Override
    public JsValue getProto() {
        return Top.getThreadLocalTop().getStringClass().getPrototype();
    }


    @Override
    public JsString toJsString() {
        return this;
    }

    @Override
    public JsBoolean toJsBoolean() {
        return value.isEmpty() ? JsBoolean.getFalseInstance() : JsBoolean.getTrueInstance();
    }

    @Override
    public JsNumber toJsNumber() {
        return new JsNumber(value);
    }

    public String getValue() {
        return value;
    }
}
