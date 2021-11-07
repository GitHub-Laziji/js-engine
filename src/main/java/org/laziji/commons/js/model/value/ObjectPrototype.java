package org.laziji.commons.js.model.value;

public class ObjectPrototype extends JsObject {

    {
        addInternalProperty("toString", (caller, arguments) -> new JsStringObject(caller.toString()));
    }

    @Override
    public JsValue getProto() {
        return JsUndefined.getInstance();
    }
}
