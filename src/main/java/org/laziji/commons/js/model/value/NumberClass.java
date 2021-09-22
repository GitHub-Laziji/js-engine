package org.laziji.commons.js.model.value;

public class NumberClass extends InternalFunction {

    public NumberClass() {
        super(null);
    }

    @Override
    public Value getPrototype() {
        return Top.getNumberPrototype();
    }
}