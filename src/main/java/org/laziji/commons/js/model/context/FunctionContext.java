package org.laziji.commons.js.model.context;

import org.laziji.commons.js.model.value.object.JsObject;
import org.laziji.commons.js.model.value.JsValue;

import java.util.List;

public class FunctionContext extends InstanceContext {

    private JsValue returnValue;

    private List<JsValue> arguments;

    public FunctionContext(JsObject instance) {
        super(instance);
    }

    public void setArguments(List<JsValue> arguments) {
        this.arguments = arguments;
    }

    public List<JsValue> getArguments() {
        return arguments;
    }

    public void setReturnValue(JsValue returnValue) {
        this.returnValue = returnValue;
    }

    public JsValue getReturnValue() {
        return returnValue;
    }
}
