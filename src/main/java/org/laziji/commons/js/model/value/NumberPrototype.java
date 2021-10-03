package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.value.env.Top;

public class NumberPrototype extends JsNumber {

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
