package org.laziji.commons.js.model.value;

import java.util.List;

public class ArrayValue extends ObjectValue {

    public ArrayValue(List<Value> values) throws Exception {
        put("length", new NumberValue(values.size()));
        for (int i = 0; i < values.size(); i++) {
            put(i + "", values.get(i));
        }
    }

    @Override
    public void put(String name, Value value) throws Exception {
        // TODO length
        super.put(name, value);
    }

    @Override
    public String toString() {
        return "[...]";
    }

}
