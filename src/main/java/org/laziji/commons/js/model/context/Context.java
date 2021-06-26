package org.laziji.commons.js.model.context;


import org.laziji.commons.js.model.context.name.Name;
import org.laziji.commons.js.model.value.Value;

public interface Context {

    void defined(Name name, Value value) throws Exception;

    void put(String name, Value value) throws Exception;

    Value get(String name) throws Exception;

    Entry getEntry(String name) throws Exception;

    void close();

    boolean isClose();

    class Entry {
        private Name name;
        private Value value;

        public Entry(Name name, Value value) {
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
