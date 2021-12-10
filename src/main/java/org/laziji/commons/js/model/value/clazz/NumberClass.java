package org.laziji.commons.js.model.value.clazz;

import org.laziji.commons.js.model.value.InternalFunction;
import org.laziji.commons.js.model.value.object.JsObject;
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