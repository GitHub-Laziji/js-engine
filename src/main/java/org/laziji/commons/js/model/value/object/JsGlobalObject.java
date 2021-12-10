package org.laziji.commons.js.model.value.object;

import org.laziji.commons.js.model.value.clazz.*;
import org.laziji.commons.js.model.value.env.Top;

public class JsGlobalObject extends JsObject {

    private static ObjectClass objectClass;
    private static ArrayClass arrayClass;
    private static FunctionClass functionClass;
    private static StringClass stringClass;
    private static NumberClass numberClass;

    {
        addInternalProperty("String", Top::getStringClass);
    }

}
