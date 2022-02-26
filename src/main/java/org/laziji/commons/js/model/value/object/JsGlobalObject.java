package org.laziji.commons.js.model.value.object;

import org.laziji.commons.js.model.value.env.Top;

public class JsGlobalObject extends JsObject {

    {
        addInternalProperty("Object", Top::getStringClass);
        addInternalProperty("Function", Top::getFunctionClass);
        addInternalProperty("String", Top::getStringClass);
        addInternalProperty("Number", Top::getNumberClass);
        addInternalProperty("Boolean", Top::getBooleanClass);

        addInternalProperty("Promise", Top::getPromiseClass);
    }

}
