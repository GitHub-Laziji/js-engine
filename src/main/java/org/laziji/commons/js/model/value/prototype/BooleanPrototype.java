package org.laziji.commons.js.model.value.prototype;

import org.laziji.commons.js.model.value.object.JsBooleanObject;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;

public class BooleanPrototype extends JsBooleanObject {

    {

    }

    public BooleanPrototype() {
        super(false);
    }

    @Override
    public JsValue getProto() {
        return Top.getThreadLocalTop().getObjectClass().getPrototype();
    }
}
