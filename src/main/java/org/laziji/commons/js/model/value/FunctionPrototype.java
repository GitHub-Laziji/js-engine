package org.laziji.commons.js.model.value;

public class FunctionPrototype extends InternalFunction {

    {

    }

    public FunctionPrototype() {
        super(null);
        proto = Top.getObjectPrototype();
    }
}
