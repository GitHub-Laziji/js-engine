package org.laziji.commons.js.model.value;

public class ObjectPrototype extends ObjectValue {

    {
        addInternalProperty("toString", (caller, arguments) -> new StringValue(caller.toString()));
    }

    @Override
    public Value getProto() {
        return UndefinedValue.getInstance();
    }
}
