package org.laziji.commons.js.model.value;

import java.util.List;


public class InternalFunction extends FunctionValue {
    private Handler handler;

    public InternalFunction(Handler handler) {
        this.handler = handler;
    }

    @Override
    public Value call(ObjectValue caller, List<Value> arguments) throws Exception {
        return handler.call(caller, arguments);
    }

    @FunctionalInterface
    public interface Handler {
        Value call(ObjectValue caller, List<Value> arguments) throws Exception;
    }

    @Override
    public String toString() {
        return "function() { [native code] }";
    }
}
