package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.value.prototype.NumberPrototype;

public class NumberClass extends InternalFunction {

    public NumberClass() {
        super(null);
    }

    @Override
    protected JsObject initPrototype() {
        return new NumberPrototype();
    }
}