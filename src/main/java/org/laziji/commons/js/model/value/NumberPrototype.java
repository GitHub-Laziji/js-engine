package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.value.env.Top;

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
