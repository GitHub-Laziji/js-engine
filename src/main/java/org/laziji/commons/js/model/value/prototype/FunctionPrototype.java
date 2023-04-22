package org.laziji.commons.js.model.value.prototype;

import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.object.JsFunction;

public class FunctionPrototype extends JsFunction {

    {

    }

    public FunctionPrototype() {
    }

    @Override
    public JsValue getProto() {
        return Top.getThreadLocalTop().getObjectClass().getPrototype();
    }
}
