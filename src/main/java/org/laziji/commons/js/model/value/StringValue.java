package org.laziji.commons.js.model.value;

public class StringValue extends ObjectValue {

    private String value;

    public static StringValue create(String value) {
        return new StringValue(value);
    }

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
