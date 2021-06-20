package org.laziji.commons.js.model.context;


import org.laziji.commons.js.model.context.name.Name;
import org.laziji.commons.js.model.value.Value;

public interface Context {

    void defined(Name name, Value value) throws Exception;

    void put(String name, Value value) throws Exception;

    Value get(String name) throws Exception;

    void close();

    boolean isClose();

}
