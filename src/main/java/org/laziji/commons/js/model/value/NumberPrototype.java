package org.laziji.commons.js.model.value;

public class NumberPrototype extends NumberValue {

    {

    }

    public NumberPrototype() {
        super(0);
    }

    @Override
    public Value getProto() {
        return Top.getObjectClass().getPrototype();
    }
}
