package org.laziji.commons.js.model.context;


import org.laziji.commons.js.model.value.ObjectValue;
import org.laziji.commons.js.model.value.Value;

public class FunctionContext extends BaseContext implements InstanceContext {

    private Value returnValue;
    private ObjectValue instance;

    public FunctionContext(ObjectValue instance) {
        this.instance = instance;
    }

    public ObjectValue getInstance() {
        return instance;
    }

    public void setReturnValue(Value returnValue) {
        this.returnValue = returnValue;
    }

    public Value getReturnValue() {
        return returnValue;
    }
}
