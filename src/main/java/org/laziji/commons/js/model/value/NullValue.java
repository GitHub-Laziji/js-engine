package org.laziji.commons.js.model.value;

public class NullValue extends BaseValue {

    private static final NullValue instance = new NullValue();

    public NullValue() {
    }

    public static NullValue getInstance() {
        return instance;
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
