package org.laziji.commons.js.model.value;

public class NullValue extends BaseValue {

    public NullValue() {
    }

    @Override
    public BooleanValue toBooleanValue() {
        return new BooleanValue(false);
    }

    @Override
    public String toString() {
        return "null";
    }
}
