package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.primitive.JsString;

public class JsStringObject extends JsObject {

    private String value;

    public JsStringObject(String value) {
        this.value = value;
    }

    @Override
    public JsValue getProto() {
        return Top.getStringClass().getPrototype();
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public JsString toJsString() {
        return new JsString(value);
    }
}
