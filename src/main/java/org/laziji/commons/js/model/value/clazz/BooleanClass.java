package org.laziji.commons.js.model.value.clazz;

import org.laziji.commons.js.model.value.InternalFunction;
import org.laziji.commons.js.model.value.object.JsObject;
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
