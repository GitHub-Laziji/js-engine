package org.laziji.commons.js.model.value;

public class GlobalValue extends ObjectValue {

    private static final GlobalValue instance = new GlobalValue();

    public static GlobalValue getInstance() {
        return instance;
    }

}