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
        try {
            StringBuilder result = new StringBuilder();
            result.append("[");
            Value lengthValue = context.get("length");
            if (!(lengthValue instanceof NumberValue)) {
                throw new Exception();
            }
            int length = (int) ((NumberValue) lengthValue).getValue();
            for (int i = 0; i < length; i++) {
                result.append(context.get(i + "").toString());
                if (i != length - 1) {
                    result.append(", ");
                }
            }
            result.append("]");
            return result.toString();
        } catch (Exception ignored) {
        }
        return "[...]";
    }

}
