package org.laziji.commons.js.model.value.prototype;

import org.laziji.commons.js.model.value.object.JsObject;
import org.laziji.commons.js.model.value.object.JsStringObject;
import org.laziji.commons.js.model.value.primitive.JsUndefined;
import org.laziji.commons.js.model.value.JsValue;

public class ObjectPrototype extends JsObject {

    {
        addInternalProperty("toString", (caller, arguments) -> new JsStringObject(caller.toString()));
    }

    @Override
    public JsValue getProto() {
        return JsUndefined.getInstance();
    }
}
