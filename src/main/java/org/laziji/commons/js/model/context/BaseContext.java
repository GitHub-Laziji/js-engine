package org.laziji.commons.js.model.context;

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
    public void put(Name name, Value value) {
        this.context.put(name.getName(), new Item(name, value));
    }

    @Override
    public Value get(Name name) {
        return this.context.get(name.getName()).getValue();
    }

    @Override
    public Value get(String name) {
        return this.context.get(name).getValue();
    }

    @Override
    public boolean isClose() {
        return close;
    }

    @Override
    public void close() {
        this.close = true;
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
