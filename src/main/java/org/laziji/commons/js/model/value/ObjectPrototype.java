package org.laziji.commons.js.model.value;

public class ObjectPrototype extends ObjectValue {

    {
        addProperty("toString", new InternalFunction((caller, manager, arguments) -> StringValue.create("-")), ObjectPropertyType.READ_ONLY);
    }

    @Override
    public ObjectValue getProto() {
        return null;
    }
}
