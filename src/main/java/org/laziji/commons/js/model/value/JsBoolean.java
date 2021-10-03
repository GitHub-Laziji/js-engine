package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.value.env.Top;

public class JsBoolean extends JsObject {

    private static final JsBoolean trueInstance = new JsBoolean(true);
    private static final JsBoolean falseInstance = new JsBoolean(false);

    private boolean value;

    public static JsBoolean getTrueInstance() {
        return trueInstance;
    }

    public static JsBoolean getFalseInstance() {
        return falseInstance;
    }

    public JsBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public JsValue getProto() {
        return Top.getBooleanClass().getPrototype();
    }

    @Override
    public JsBoolean toJsBoolean() {
        return this;
    }

    public boolean getValue() {
        return value;
    }

}
