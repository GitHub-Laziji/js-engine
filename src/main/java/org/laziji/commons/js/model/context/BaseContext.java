package org.laziji.commons.js.model.context;

import com.alibaba.fastjson.JSON;
import org.laziji.commons.js.exception.ReferenceException;
import org.laziji.commons.js.exception.SyntaxException;
import org.laziji.commons.js.exception.TypeException;
import org.laziji.commons.js.model.value.Value;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseContext implements Context {

    protected Map<String, ContextProperty> context;
    protected boolean close = false;

    public BaseContext() {
        this.context = new ConcurrentHashMap<>();
    }

    @Override
    public Value addProperty(String key, Value value, ContextPropertyType type) throws SyntaxException {
        if (context.containsKey(key)) {
            throw new SyntaxException("Identifier '%s' has already been declared", key);
        }
        context.put(key, new ContextProperty(key, value, type));
        return value;
    }

    @Override
    public Value addProperty(String key, Value value) throws ReferenceException, TypeException {
        if (context.containsKey(key)) {
            ContextProperty property = context.get(key);
            if (property.getType() == ContextPropertyType.CONST) {
                throw new TypeException("Assignment to constant variable");
            }
            property.setValue(value);
            return value;
        }
        throw new ReferenceException("%s is not defined", key);
    }

    @Override
    public Value getProperty(String key) throws ReferenceException {
        if (!context.containsKey(key)) {
            throw new ReferenceException("%s is not defined", key);
        }
        return context.get(key).getValue();
    }

    @Override
    public boolean hasProperty(String key) {
        return context.containsKey(key);
    }

    @Override
    public boolean isClose() {
        return close;
    }

    @Override
    public void close() {
        this.close = true;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(context, true);
    }

    @Override
    public String toSimpleString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, ContextProperty> entry : context.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue().getValue().toString()).append('\n');
        }
        return result.toString();
    }
}
