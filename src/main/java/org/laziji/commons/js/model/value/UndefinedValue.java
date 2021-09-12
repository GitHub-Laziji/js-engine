package org.laziji.commons.js.model.value;

public class UndefinedValue extends BaseValue {

    private static final UndefinedValue instance = new UndefinedValue();

    public UndefinedValue() {
    }

    public static UndefinedValue getInstance() {
        return instance;
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
