package org.laziji.commons.js.model.value.object;

import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.primitive.JsBoolean;

public class JsBooleanObject extends JsObject {

    private final JsBoolean primitiveValue;

    public JsBooleanObject(boolean value) {
        primitiveValue = new JsBoolean(value);
    }

    @Override
    public JsValue getProto() {
        return Top.getThreadLocalTop().getBooleanClass().getPrototype();
    }

    @Override
    public JsBoolean toJsBoolean() {
        return primitiveValue;
    }

}
