package org.laziji.commons.js.model.value;

public class NumberPrototype extends NumberValue {

    {

    }

    public NumberPrototype() {
        super(0);
        proto = Top.getObjectPrototype();
    }
}
