package org.laziji.commons.js.model.value;

import java.util.HashMap;
import java.util.Map;

public class ObjectValue extends BaseValue {

    private Map<String, ObjectProperty> properties = new HashMap<>();

    public ObjectValue() {
    }

    public Value getProto() {
        return Top.getObjectPrototype();
    }

    public void removeProperty(String key) {
        if (!properties.containsKey(key)) {
            return;
        }
        if (properties.get(key).getType() == ObjectPropertyType.READ_ONLY) {
            return;
        }
        properties.remove(key);
    }

    public Value addProperty(String key, Value value, ObjectPropertyType type) {
        if (properties.containsKey(key)) {
            ObjectProperty property = properties.get(key);
            if (property.getType() == ObjectPropertyType.READ_ONLY) {
                return property.getValue();
            }
            property.setValue(value);
            return value;
        }
        properties.put(key, new ObjectProperty(key, value, type));
        return value;
    }

    public Value addProperty(String key, Value value) {
        return addProperty(key, value, ObjectPropertyType.NONE);
    }

    public Value getProperty(String key) {
        if (properties.containsKey(key)) {
            return properties.get(key).getValue();
        }
        Value proto = getProto();
        if (proto != null && proto instanceof ObjectValue) {
            return ((ObjectValue) proto).getProperty(key);
        }
        return UndefinedValue.getInstance();
    }

    public boolean hasProperty(String key) {
        return properties.containsKey(key);
    }

    public enum ObjectPropertyType {
        NONE,
        READ_ONLY
    }

    public static class ObjectProperty {
        private String key;
        private Value value;
        private ObjectPropertyType type;
        private FunctionValue getter;
        private FunctionValue setter;

        public ObjectProperty(String key, Value value, ObjectPropertyType type) {
            this.key = key;
            this.value = value;
            this.type = type;
        }

        public ObjectProperty(String key, Value value) {
            this.key = key;
            this.value = value;
            this.type = ObjectPropertyType.NONE;
        }

        public ObjectProperty(String key) {
            this.key = key;
            this.value = UndefinedValue.getInstance();
            this.type = ObjectPropertyType.NONE;
        }

        public String getKey() {
            return key;
        }

        public ObjectPropertyType getType() {
            return type;
        }

        public void setValue(Value value) {

            //TODO setter
            this.value = value;
        }

        public Value getValue() {
            //TODO getter
            return value;
        }
    }
}
