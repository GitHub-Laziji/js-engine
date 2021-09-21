package org.laziji.commons.js.model.value;

public class ObjectPrototype extends ObjectValue {

    {
        addInternalProperty("toString", (caller, contexts, arguments) -> new StringValue(caller.toString()));
    }

    @Override
    public Value getProto() {
        return UndefinedValue.getInstance();
    }
}
