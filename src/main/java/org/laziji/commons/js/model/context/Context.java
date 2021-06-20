package org.laziji.commons.js.model.context;


import org.laziji.commons.js.model.context.name.Name;
import org.laziji.commons.js.model.value.Value;

public interface Context {

    void put(Name name, Value value);

    Value get(Name name);

    Value get(String name);

    void close();

    boolean isClose();

}
