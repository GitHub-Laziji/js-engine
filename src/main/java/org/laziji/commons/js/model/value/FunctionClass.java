package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.value.prototype.FunctionPrototype;

public class FunctionClass extends InternalFunction {

    public FunctionClass() {
        super(null);
    }

    @Override
    protected JsObject initPrototype() {
        return new FunctionPrototype();
    }
}
