package org.laziji.commons.js.model.value;

public class FunctionClass extends InternalFunction {

    public FunctionClass() {
        super(null);
    }

    @Override
    public Value getPrototype() {
        return Top.getFunctionPrototype();
    }
}
