package org.laziji.commons.js.model.value.prototype;

import org.laziji.commons.js.model.value.object.JsNumberObject;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;

public class NumberPrototype extends JsNumberObject {

    {

    }

    public NumberPrototype() {
        super(0);
    }

    @Override
    public JsValue getProto() {
        return Top.getObjectClass().getPrototype();
    }
}
