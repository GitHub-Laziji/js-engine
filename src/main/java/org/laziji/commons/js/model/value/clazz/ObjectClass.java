package org.laziji.commons.js.model.value.clazz;

import org.laziji.commons.js.model.value.InternalFunction;
import org.laziji.commons.js.model.value.object.JsObject;
import org.laziji.commons.js.model.value.prototype.ObjectPrototype;

public class ObjectClass extends InternalFunction {

    public ObjectClass() {
        super((caller, args) -> new JsObject());
    }

    @Override
    protected JsObject initPrototype() {
        return new ObjectPrototype();
    }
}
