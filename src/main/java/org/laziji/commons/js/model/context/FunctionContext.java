package org.laziji.commons.js.model.context;


import org.laziji.commons.js.model.value.Value;

public class FunctionContext extends BaseContext {

    private Value returnValue;
    private boolean function;

    public FunctionContext(boolean function) {
        this.function = function;
    }

    public boolean isFunction() {
        return function;
    }

    public boolean isLambda() {
        return !function;
    }

    public void setReturnValue(Value returnValue) {
        this.returnValue = returnValue;
    }

    public Value getReturnValue() {
        return returnValue;
    }
}
