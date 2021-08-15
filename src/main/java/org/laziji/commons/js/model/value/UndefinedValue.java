package org.laziji.commons.js.model.value;

public class UndefinedValue extends BaseValue {

    public UndefinedValue() {
    }

    @Override
    public BooleanValue toBooleanValue() {
        return new BooleanValue(false);
    }

    @Override
    public String toString() {
        return "undefined";
    }
}
