package org.laziji.commons.js.model.value;

public class BooleanPrototype extends NumberValue {

    {

    }

    public BooleanPrototype() {
        super(0);
        proto = Top.getObjectPrototype();
    }
}
