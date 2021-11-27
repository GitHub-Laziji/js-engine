package org.laziji.commons.js.model.value;

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
