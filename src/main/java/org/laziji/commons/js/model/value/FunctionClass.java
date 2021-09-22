package org.laziji.commons.js.model.value;

public class FunctionClass extends InternalFunction {

    public FunctionClass() {
        super(null);
    }

    @Override
    protected ObjectValue initPrototype() {
        return new FunctionPrototype();
    }
}
