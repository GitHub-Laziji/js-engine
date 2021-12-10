package org.laziji.commons.js.model.value.object;

import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.primitive.JsString;

public class JsStringObject extends JsObject {

    private JsString primitiveValue;

    public JsStringObject(String value) {
        primitiveValue = new JsString(value);
    }

    @Override
    public JsValue getProto() {
        return Top.getStringClass().getPrototype();
    }

    @Override
    public String toString() {
        return primitiveValue.toString();
    }

    @Override
    public JsString toJsString() {
        return primitiveValue;
    }
}
