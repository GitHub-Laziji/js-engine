package org.laziji.commons.js.model.context;

import org.laziji.commons.js.model.value.Value;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseContext implements Context {

    private Map<String, Value> context;
    private boolean close = false;

    public BaseContext() {
        this.context = new ConcurrentHashMap<>();
    }

    @Override
    public void put(String name, Value value) {
        this.context.put(name, value);
    }

    @Override
    public Value get(String name) {
        return this.context.get(name);
    }

    @Override
    public boolean isClose() {
        return close;
    }

    @Override
    public void close() {
        this.close = true;
    }

}
