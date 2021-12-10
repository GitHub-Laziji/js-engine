package org.laziji.commons.js.model.value.clazz;

import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.value.InternalFunction;
import org.laziji.commons.js.model.value.object.JsObject;
import org.laziji.commons.js.model.value.object.JsStringObject;
import org.laziji.commons.js.model.value.prototype.StringPrototype;

public class StringClass extends InternalFunction {

    public StringClass() {
        super((caller, arguments) -> new JsStringObject(arguments.size() > 0 ? arguments.get(0).toString() : ""));
    }

    @Override
    protected JsObject initPrototype() {
        return new StringPrototype();
    }
}
