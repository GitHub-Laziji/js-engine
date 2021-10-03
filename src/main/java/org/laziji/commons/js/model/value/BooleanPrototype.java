package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.value.env.Top;

public class BooleanPrototype extends JsBoolean {

    {

    }

    public BooleanPrototype() {
        super(false);
    }

    @Override
    public JsValue getProto() {
        return Top.getObjectClass().getPrototype();
    }
}
