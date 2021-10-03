package org.laziji.commons.js.model.value;

public class JsNull extends BaseJsValue {

    private static final JsNull instance = new JsNull();

    public JsNull() {
    }

    public static JsNull getInstance() {
        return instance;
    }

    @Override
    public JsBoolean toJsBoolean() {
        return new JsBoolean(false);
    }

    @Override
    public String toString() {
        return "null";
    }
}
