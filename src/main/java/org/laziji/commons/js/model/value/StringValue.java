package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.value.env.Top;

public class StringValue extends ObjectValue {

    private String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public Value getProto() {
        return Top.getStringClass().getPrototype();
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
