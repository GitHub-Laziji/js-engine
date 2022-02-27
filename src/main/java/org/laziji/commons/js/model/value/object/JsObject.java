package org.laziji.commons.js.model.value.object;

import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.exception.TypeException;
import org.laziji.commons.js.model.value.BaseJsValue;
import org.laziji.commons.js.model.value.InternalFunction;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.primitive.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class JsObject extends BaseJsValue {

    private Map<String, ObjectProperty> properties = new HashMap<>();
    private JsObject proto;

    {
        addInternalProperty("__proto__", this::getProto);
    }

    public static JsObject cast(JsValue value) throws Exception {
        if (value == null) {
            throw new RunException();
        }
        if (value instanceof JsNull) {
            throw new TypeException("Cannot read property of null");
        }
        if (value instanceof JsUndefined) {
            throw new TypeException("Cannot read property of undefined");
        }
        if (!(value instanceof JsObject)) {
            throw new TypeException();
        }
        return (JsObject) value;
    }

    public JsObject() {
    }

    public JsObject(JsObject proto) {
        this.proto = proto;
    }

    public JsValue getProto() {
        if (proto != null) {
            return proto;
        }
        return Top.getThreadLocalTop().getObjectClass().getPrototype();
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

    public JsValue addProperty(String key, JsValue value, ObjectPropertyType type) {
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

    public JsValue addProperty(String key, JsValue value) {
        return addProperty(key, value, ObjectPropertyType.NONE);
    }

    protected void addInternalProperty(String key, Supplier<JsValue> handler) {
        properties.put(key, new ObjectProperty(key, handler, ObjectPropertyType.READ_ONLY));
    }

    protected void addInternalProperty(String key, InternalFunction.Handler handler) {
        properties.put(key, new ObjectProperty(key, new InternalFunction(handler), ObjectPropertyType.READ_ONLY));
    }

    protected void addInternalProperty(String key, JsValue value) {
        properties.put(key, new ObjectProperty(key, value, ObjectPropertyType.READ_ONLY));
    }

    public JsValue getProperty(String key) {
        if (properties.containsKey(key)) {
            return properties.get(key).getValue();
        }
        JsValue proto = getProto();
        if (proto instanceof JsObject) {
            return ((JsObject) proto).getProperty(key);
        }
        return JsUndefined.getInstance();
    }

    public boolean hasProperty(String key) {
        return properties.containsKey(key);
    }

    @Override
    public JsBoolean toJsBoolean() {
        return JsBoolean.getTrueInstance();
    }

    @Override
    public JsNumber toJsNumber() {
        return new JsNumber(0);
    }

    @Override
    public JsString toJsString() {
        return new JsString("[object Object]");
    }

    public enum ObjectPropertyType {
        NONE, READ_ONLY
    }

    public static class ObjectProperty {
        private String key;
        private JsValue value;
        private ObjectPropertyType type;
        private JsFunction getter;
        private JsFunction setter;

        private Supplier<JsValue> handler;

        public ObjectProperty(String key, JsValue value, ObjectPropertyType type) {
            this.key = key;
            this.value = value;
            this.type = type;
        }

        public ObjectProperty(String key, Supplier<JsValue> handler, ObjectPropertyType type) {
            this.key = key;
            this.handler = handler;
            this.type = type;
        }

        public ObjectProperty(String key, JsValue value) {
            this.key = key;
            this.value = value;
            this.type = ObjectPropertyType.NONE;
        }

        public ObjectProperty(String key) {
            this.key = key;
            this.value = JsUndefined.getInstance();
            this.type = ObjectPropertyType.NONE;
        }

        public String getKey() {
            return key;
        }

        public ObjectPropertyType getType() {
            return type;
        }

        public void setValue(JsValue value) {
            if (type == ObjectPropertyType.READ_ONLY) {
                return;
            }
            //TODO setter
            this.value = value;
        }

        public JsValue getValue() {
            if (handler != null) {
                return handler.get();
            }
            //TODO getter
            return value;
        }
    }
}
