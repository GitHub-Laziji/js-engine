package org.laziji.commons.js.model.value;

public class NullValue extends BaseValue {

    private static final NullValue instance = new NullValue();

    private NullValue() {

    }

    public static NullValue getInstance() {
        return instance;
    }

    @Override
    public BooleanValue toBoolean() {
        return BooleanValue.getFalseInstance();
    }

    @Override
    public String toString() {
        return "null";
    }
}
