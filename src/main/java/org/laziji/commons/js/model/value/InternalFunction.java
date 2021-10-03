package org.laziji.commons.js.model.value;

import java.util.List;


public class InternalFunction extends JsFunction {
    private Handler handler;

    public InternalFunction(Handler handler) {
        this.handler = handler;
    }

    @Override
    public JsValue call(JsObject caller, List<JsValue> arguments) throws Exception {
        return handler.call(caller, arguments);
    }

    @FunctionalInterface
    public interface Handler {
        JsValue call(JsObject caller, List<JsValue> arguments) throws Exception;
    }

    @Override
    public String toString() {
        return "function() { [native code] }";
    }
}
