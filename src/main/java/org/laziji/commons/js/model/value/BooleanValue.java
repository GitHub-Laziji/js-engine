package org.laziji.commons.js.model.value;

public class BooleanValue extends BaseValue {

    private boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public BooleanValue toBoolean() {
        return this;
    }

    public boolean getValue() {
        return value;
    }

}
