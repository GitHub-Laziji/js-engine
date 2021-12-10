package org.laziji.commons.js.model.value.clazz;

import org.laziji.commons.js.model.value.InternalFunction;
import org.laziji.commons.js.model.value.object.JsObject;
import org.laziji.commons.js.model.value.prototype.FunctionPrototype;

public class FunctionClass extends InternalFunction {

    public FunctionClass() {
        super((caller, args) -> null);
    }

    @Override
    protected JsObject initPrototype() {
        return new FunctionPrototype();
    }
}
