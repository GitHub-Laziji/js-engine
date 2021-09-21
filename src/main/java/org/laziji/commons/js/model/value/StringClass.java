package org.laziji.commons.js.model.value;

public class StringClass extends InternalFunction {

    public StringClass() {
        super(null);
    }

    @Override
    public ObjectValue getPrototype() {
        return Top.getStringPrototype();
    }
}
