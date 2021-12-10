package org.laziji.commons.js.model.value.clazz;

import org.laziji.commons.js.model.value.InternalFunction;
import org.laziji.commons.js.model.value.object.JsArray;

public class ArrayClass extends InternalFunction {

    public ArrayClass() {
        super((caller, args) -> new JsArray());
    }
}
