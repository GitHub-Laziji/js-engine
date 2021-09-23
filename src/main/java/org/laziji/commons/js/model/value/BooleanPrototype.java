package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.value.env.Top;

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
