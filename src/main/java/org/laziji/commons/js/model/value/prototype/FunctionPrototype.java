package org.laziji.commons.js.model.value.prototype;

import org.laziji.commons.js.model.value.InternalFunction;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;

public class FunctionPrototype extends InternalFunction {

    {

    }

    public FunctionPrototype() {
        super(null);
    }

    @Override
    public JsValue getProto() {
        return Top.getObjectClass().getPrototype();
    }
}
