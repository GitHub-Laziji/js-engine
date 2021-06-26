package org.laziji.commons.js.model.value;

public class UndefinedValue extends BaseValue {

    public UndefinedValue() {
    }

    @Override
    public BooleanValue toBoolean() {
        return new BooleanValue(false);
    }

    @Override
    public String toString() {
        return "undefined";
    }
}
