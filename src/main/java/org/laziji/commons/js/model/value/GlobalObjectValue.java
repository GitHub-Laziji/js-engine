package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.value.env.Top;

public class GlobalObjectValue extends ObjectValue {

    private static ObjectClass objectClass;
    private static ArrayClass arrayClass;
    private static FunctionClass functionClass;
    private static StringClass stringClass;
    private static NumberClass numberClass;

    {
        addInternalProperty("String", Top::getStringClass);
    }

}
