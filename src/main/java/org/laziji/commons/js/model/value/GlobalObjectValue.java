package org.laziji.commons.js.model.value;

public class GlobalObjectValue extends ObjectValue {

    private static GlobalObjectValue instance;

    private static ObjectClass objectClass;
    private static ArrayClass arrayClass;
    private static FunctionClass functionClass;
    private static StringClass stringClass;
    private static NumberClass numberClass;

    static {
        instance = new GlobalObjectValue();
        instance.init();
    }

    public static GlobalObjectValue getInstance() {
        return instance;
    }

    private GlobalObjectValue() {
    }

    private void init(){

    }
}
