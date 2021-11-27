package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.value.prototype.ObjectPrototype;

public class ObjectClass extends InternalFunction {

    public ObjectClass() {
        super(null);
    }

    @Override
    protected JsObject initPrototype() {
        return new ObjectPrototype();
    }
}
