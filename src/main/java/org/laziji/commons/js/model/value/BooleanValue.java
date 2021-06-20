package org.laziji.commons.js.model.value;

public class BooleanValue extends BaseValue {

    private static final BooleanValue trueInstance = new BooleanValue(true);
    private static final BooleanValue falseInstance = new BooleanValue(false);

    private boolean value;

    private BooleanValue(boolean value) {
        this.value = value;
    }

    public static BooleanValue getTrueInstance() {
        return trueInstance;
    }

    public static BooleanValue getFalseInstance() {
        return falseInstance;
    }

    @Override
    public BooleanValue toBoolean() {
        return this;
    }

    public boolean getValue() {
        return value;
    }

}
