package org.laziji.commons.js.model.value;

public class GlobalObjectValue extends ObjectValue {

    private static GlobalObjectValue instance = new GlobalObjectValue();

    public static GlobalObjectValue getInstance() {
        return instance;
    }

    private GlobalObjectValue() {
    }
}
