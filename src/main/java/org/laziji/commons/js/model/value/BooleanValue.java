package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.value.env.Top;

public class BooleanValue extends ObjectValue {

    private static final BooleanValue trueInstance = new BooleanValue(true);
    private static final BooleanValue falseInstance = new BooleanValue(false);

    private boolean value;

    public static BooleanValue getTrueInstance() {
        return trueInstance;
    }

    public static BooleanValue getFalseInstance() {
        return falseInstance;
    }

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public Value getProto() {
        return Top.getBooleanClass().getPrototype();
    }

    @Override
    public BooleanValue toBooleanValue() {
        return this;
    }

    public boolean getValue() {
        return value;
    }

}
