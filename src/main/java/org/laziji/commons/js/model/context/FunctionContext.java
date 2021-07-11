package org.laziji.commons.js.model.context;

import org.laziji.commons.js.model.value.ObjectValue;
import org.laziji.commons.js.model.value.Value;

public class FunctionContext extends InstanceContext {

    private Value returnValue;

    public FunctionContext(ObjectValue instance) {
        super(instance);
    }

    public void setReturnValue(Value returnValue) {
        this.returnValue = returnValue;
    }

    public Value getReturnValue() {
        return returnValue;
    }
}
