package org.laziji.commons.js.model.value;

public class BooleanValue extends ObjectValue {

    private boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public Value getProto() {
        return Top.getBooleanPrototype();
    }

    @Override
    public BooleanValue toBooleanValue() {
        return this;
    }

    public boolean getValue() {
        return value;
    }

}
