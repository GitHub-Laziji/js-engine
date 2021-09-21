package org.laziji.commons.js.model.value;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ObjectValue extends BaseValue {

    private Map<String, ObjectProperty> properties = new HashMap<>();

    {
        addInternalProperty("__proto__", this::getProto);
    }

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
        System.out.println(this.getClass().getSimpleName() + ":" + key + "=" + value);
        return addProperty(key, value, ObjectPropertyType.NONE);
    }

    protected void addInternalProperty(String key, Supplier<Value> handler) {
        properties.put(key, new ObjectProperty(key, handler));
    }

    public Value getProperty(String key) {
        if (properties.containsKey(key)) {
            return properties.get(key).getValue();
        }
        Value proto = getProto();
        if (proto instanceof ObjectValue) {
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

        private Supplier<Value> handler;

        public ObjectProperty(String key, Value value, ObjectPropertyType type) {
            this.key = key;
            this.value = value;
            this.type = type;
        }

        public ObjectProperty(String key, Supplier<Value> handler) {
            this.key = key;
            this.handler = handler;
            this.type = ObjectPropertyType.READ_ONLY;
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
            if (type == ObjectPropertyType.READ_ONLY) {
                return;
            }
            //TODO setter
            this.value = value;
        }

        public Value getValue() {
            if (handler != null) {
                return handler.get();
            }
            //TODO getter
            return value;
        }
    }
}
