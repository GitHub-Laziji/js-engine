package org.laziji.commons.js.model.context;

import com.alibaba.fastjson.JSON;
import org.laziji.commons.js.exception.ReferenceException;
import org.laziji.commons.js.exception.SyntaxException;
import org.laziji.commons.js.exception.TypeException;
import org.laziji.commons.js.model.context.name.Name;
import org.laziji.commons.js.model.value.Value;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseContext implements Context {

    private Map<String, Item> context;
    private boolean close = false;

    public BaseContext() {
        this.context = new ConcurrentHashMap<>();
    }

    @Override
    public void defined(Name name, Value value) throws SyntaxException {
        if (this.context.containsKey(name.getName())) {
            throw new SyntaxException("Identifier '%s' has already been declared", name.getName());
        }
        if (!name.isVariable() && value == null) {
            throw new SyntaxException("Missing initializer in const declaration");
        }
        this.context.put(name.getName(), new Item(name, value));
    }

    @Override
    public void put(String name, Value value) throws ReferenceException, TypeException {
        Item item = this.context.get(name);
        if (item == null) {
            throw new ReferenceException("%s is not defined", name);
        }
        if (!item.getName().isVariable()) {
            throw new TypeException("Assignment to constant variable");
        }
        item.setValue(value);
    }

    @Override
    public Value get(String name) throws ReferenceException {
        Item item = this.context.get(name);
        if (item == null) {
            throw new ReferenceException("%s is not defined", name);
        }
        return item.getValue();
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
        return JSON.toJSONString(context);
    }

    public static class Item {
        private Name name;
        private Value value;

        public Item(Name name, Value value) {
            this.name = name;
            this.value = value;
        }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Value getValue() {
            return value;
        }

        public void setValue(Value value) {
            this.value = value;
        }
    }
}
