package org.laziji.commons.js.model.value.primitive;

import org.laziji.commons.js.model.value.object.JsObject;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;

public class JsBoolean extends JsObject {

    public static JsBoolean getTrueInstance() {
        return new JsBoolean(true);
    }

    public static JsBoolean getFalseInstance() {
        return new JsBoolean(false);
    }

    private final boolean value;

    public JsBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public JsValue addProperty(String key, JsValue value, ObjectPropertyType type) {
        return value;
    }

    @Override
    public JsValue getProto() {
        return Top.getThreadLocalTop().getBooleanClass().getPrototype();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public JsNumber toJsNumber() {
        return new JsNumber(value ? 1 : 0);
    }

    @Override
    public JsBoolean toJsBoolean() {
        return this;
    }

    @Override
    public JsString toJsString() {
        return new JsString(String.valueOf(value));
    }

    public boolean getValue() {
        return value;
    }
}