package org.laziji.commons.js.model.value;

public class BooleanClass extends InternalFunction {

    public BooleanClass() {
        super(null);
    }

    @Override
    public JsObject getPrototype() {
        return new BooleanPrototype();
    }
}
