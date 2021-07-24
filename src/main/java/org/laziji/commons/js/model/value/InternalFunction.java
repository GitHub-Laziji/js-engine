package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.manager.ScriptManager;

import java.util.List;


public class InternalFunction extends FunctionValue {
    private Handler handler;

    public InternalFunction(Handler handler) {
        super(null, null, true);
        this.handler = handler;
    }

    @Override
    public Value call(ObjectValue caller, ScriptManager manager, List<Value> arguments) throws Exception {
        return handler.call(caller, manager, arguments);
    }

    @FunctionalInterface
    public interface Handler {
        Value call(ObjectValue caller, ScriptManager manager, List<Value> arguments) throws Exception;
    }
}
