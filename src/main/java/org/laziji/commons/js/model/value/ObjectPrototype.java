package org.laziji.commons.js.model.value;

public class ObjectPrototype extends ObjectValue {

    {
        addProperty("toString", new InternalFunction((caller, manager, arguments) -> new StringValue(caller.toString())), ObjectPropertyType.READ_ONLY);
    }

    @Override
    public Value getProto() {
        return UndefinedValue.getInstance();
    }
}
