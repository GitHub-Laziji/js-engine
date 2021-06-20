package org.laziji.commons.js.model.context;


import org.laziji.commons.js.model.value.Value;

public interface Context {

    void put(String name, Value value);

    Value get(String name);

    void close();

    boolean isClose();

}
