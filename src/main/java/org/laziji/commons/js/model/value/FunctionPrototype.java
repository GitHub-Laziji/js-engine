package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.value.env.Top;

public class FunctionPrototype extends InternalFunction {

    {

    }

    public FunctionPrototype() {
        super(null);
    }

    @Override
    public Value getProto() {
        return Top.getObjectClass().getPrototype();
    }
}
