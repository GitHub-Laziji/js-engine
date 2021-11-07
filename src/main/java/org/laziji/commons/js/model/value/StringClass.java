package org.laziji.commons.js.model.value;

public class StringClass extends InternalFunction {

    public StringClass() {
        super((caller, arguments) -> new JsStringObject(arguments.size() > 0 ? arguments.get(0).toString() : ""));
    }

    @Override
    protected JsObject initPrototype() {
        return new StringPrototype();
    }
}
