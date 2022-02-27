package org.laziji.commons.js.model.value.object;

import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;

public class JsGlobalObject extends JsObject {

    {
        addInternalProperty("Object", this::getObjectClass);
        addInternalProperty("Function", this::getFunctionClass);
        addInternalProperty("String", this::getStringClass);
        addInternalProperty("Number", this::getNumberClass);
        addInternalProperty("Boolean", this::getBooleanClass);

        addInternalProperty("Promise", this::getPromiseClass);
    }

    public JsValue getObjectClass() {
        return Top.getThreadLocalTop().getObjectClass();
    }

    public JsValue getFunctionClass() {
        return Top.getThreadLocalTop().getFunctionClass();
    }

    public JsValue getStringClass() {
        return Top.getThreadLocalTop().getStringClass();
    }

    public JsValue getNumberClass() {
        return Top.getThreadLocalTop().getNumberClass();
    }

    public JsValue getBooleanClass() {
        return Top.getThreadLocalTop().getBooleanClass();
    }

    public JsValue getPromiseClass() {
        return Top.getThreadLocalTop().getPromiseClass();
    }

}
