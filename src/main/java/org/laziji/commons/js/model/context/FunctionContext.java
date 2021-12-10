package org.laziji.commons.js.model.context;

import org.laziji.commons.js.model.value.object.JsObject;
import org.laziji.commons.js.model.value.JsValue;

public class FunctionContext extends InstanceContext {

    private JsValue returnValue;

    public FunctionContext(JsObject instance) {
        super(instance);
    }

    public void setReturnValue(JsValue returnValue) {
        this.returnValue = returnValue;
    }

    public JsValue getReturnValue() {
        return returnValue;
    }
}
