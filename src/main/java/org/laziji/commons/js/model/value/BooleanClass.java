package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.value.prototype.BooleanPrototype;

public class BooleanClass extends InternalFunction {

    public BooleanClass() {
        super(null);
    }

    @Override
    public JsObject getPrototype() {
        return new BooleanPrototype();
    }
}
