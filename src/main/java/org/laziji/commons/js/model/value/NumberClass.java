package org.laziji.commons.js.model.value;

public class NumberClass extends InternalFunction {

    public NumberClass() {
        super(null);
    }

    @Override
    protected ObjectValue initPrototype() {
        return new NumberPrototype();
    }
}