package org.laziji.commons.js.model.context;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.context.name.ConstName;
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
    public void defined(Name name, Value value) throws RunException {
        if (this.context.containsKey(name.getName())) {
            throw new RunException("");
        }
        if (!name.isVariable() && value == null) {
            throw new RunException("");
        }
        this.context.put(name.getName(), new Item(name, value));
    }

    @Override
    public void put(String name, Value value) throws RunException {
        Item item = this.context.get(name);
        if (item == null) {
            throw new RunException("");
        }
        if (!item.getName().isVariable()) {
            throw new RunException("");
        }
        item.setValue(value);
    }

    @Override
    public Value get(String name) throws RunException {
        Item item = this.context.get(name);
        if (item == null) {
            throw new RunException("");
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
