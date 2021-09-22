package org.laziji.commons.js.model.value;

public class BooleanPrototype extends BooleanValue {

    {

    }

    public BooleanPrototype() {
        super(false);
    }

    @Override
    public Value getProto() {
        return Top.getObjectClass().getPrototype();
    }
}
