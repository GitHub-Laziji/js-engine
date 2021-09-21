package org.laziji.commons.js.model.value;

public class StringClass extends InternalFunction {

    public StringClass() {
        super((caller, arguments) -> new StringValue(arguments.size() > 0 ? arguments.get(0).toString() : ""));
    }

    @Override
    public ObjectValue getPrototype() {
        return Top.getStringPrototype();
    }
}
