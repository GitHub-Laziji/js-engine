package org.laziji.commons.js.model.value;

public class StringValue extends ObjectValue {

    private String value;

    public StringValue(String value) {
        this.value = value;
        proto = Top.getStringPrototype();
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
