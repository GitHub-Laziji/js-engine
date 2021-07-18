package org.laziji.commons.js.model.value;

import java.util.List;

public class ArrayValue extends ObjectValue {

    private List<Value> value;

    public ArrayValue(List<Value> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "[...]";
    }

    public List<Value> getValue() {
        return value;
    }
}
