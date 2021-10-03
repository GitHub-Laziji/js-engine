package org.laziji.commons.js.model.value;

public class JsUndefined extends BaseJsValue {

    private static final JsUndefined instance = new JsUndefined();

    public JsUndefined() {
    }

    public static JsUndefined getInstance() {
        return instance;
    }

    @Override
    public JsBoolean toJsBoolean() {
        return new JsBoolean(false);
    }

    @Override
    public String toString() {
        return "undefined";
    }
}
