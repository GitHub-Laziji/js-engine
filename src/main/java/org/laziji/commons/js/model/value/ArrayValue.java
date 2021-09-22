package org.laziji.commons.js.model.value;

import java.util.List;

public class ArrayValue extends ObjectValue {

    public ArrayValue(List<Value> values) {
        addProperty("length", new NumberValue(values.size()));
        for (int i = 0; i < values.size(); i++) {
            addProperty(i + "", values.get(i));
        }
    }

    @Override
    public Value addProperty(String name, Value value) {
        // TODO length
        return super.addProperty(name, value);
    }

    @Override
    public String toString() {
        try {
            StringBuilder result = new StringBuilder();
            result.append("[");
            Value lengthValue = getProperty("length");
            if (!(lengthValue instanceof NumberValue)) {
                throw new Exception();
            }
            int length = (int) ((NumberValue) lengthValue).getValue();
            for (int i = 0; i < length; i++) {
                result.append(getProperty(i + "").toString());
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
