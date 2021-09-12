package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.ObjectContext;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ObjectValue extends BaseValue {

    private Map<String, Dictionary> properties = new HashMap<>();
    protected final Context context;
    private FunctionValue instanceClass;
    private ObjectValue proto;

    public ObjectValue(FunctionClass instanceClass) {
        this.instanceClass = instanceClass;
        this.context = new ObjectContext();
    }

    public ObjectValue() {
        this.context = new ObjectContext();
    }

    public void setInstanceClass(FunctionValue instanceClass) {
        if (this.instanceClass != null) {
            throw new RuntimeException();
        }
        this.instanceClass = instanceClass;
    }

    public ObjectValue getInstanceClass() {
        return instanceClass;
    }

    public ObjectValue getProto() {
        if (instanceClass == null) {
            return null;
        }
        return instanceClass.getPrototype();
    }

    public void setProto(ObjectValue proto) {
        this.proto = proto;
    }

    public void put(String name, Value value) throws Exception {
        context.getEntry(name).setValue(value);
    }

    public Value get(String name) throws Exception {
        return get(name, new HashSet<>());
    }

    public Context.Entry getEntry(String name) throws Exception {
        return context.getEntry(name);
    }

    private Value get(String name, Set<ObjectValue> values) throws Exception {
        values.add(this);
        Value value = context.get(name);
        ObjectValue proto = getProto();
        if (value instanceof UndefinedValue && proto != null && !values.contains(proto)) {
            return proto.get(name, values);
        }
        return value;
    }

    @Override
    public String toString() {
        return context.toString();
    }

    public void removeProperty(String key) {
        if (!properties.containsKey(key)) {
            return;
        }
        if (properties.get(key).getType() == PropertyType.READ_ONLY) {
            return;
        }
        properties.remove(key);
    }

    public Value addProperty(String key, Value value, PropertyType type) {
        if (!properties.containsKey(key)) {
            properties.get(key).setValue(value);
        }
        properties.put(key, new Dictionary(key, value, type));
        return value;
    }

    public Value getProperty(String key) {
        if (!properties.containsKey(key)) {
            return properties.get(key).getValue();
        }
        return UndefinedValue.getInstance();
    }


    public enum PropertyType {
        NONE,
        READ_ONLY
    }

    public static class Dictionary {
        private String key;
        private Value value;
        private PropertyType type;
        private FunctionValue getter;
        private FunctionValue setter;

        public Dictionary(String key, Value value, PropertyType type) {
            this.key = key;
            this.value = value;
            this.type = type;
        }

        public Dictionary(String key, Value value) {
            this.key = key;
            this.value = value;
            this.type = PropertyType.NONE;
        }

        public Dictionary(String key) {
            this.key = key;
            this.value = UndefinedValue.getInstance();
            this.type = PropertyType.NONE;
        }

        public String getKey() {
            return key;
        }

        public PropertyType getType() {
            return type;
        }

        public void setValue(Value value) {
            if (type == PropertyType.READ_ONLY) {
                return;
            }
            //TODO setter
            this.value = value;
        }

        public Value getValue() {
            //TODO getter
            return value;
        }
    }
}
