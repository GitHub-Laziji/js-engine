package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.value.env.Top;

public class JsString extends JsObject {

    private String value;

    public JsString(String value) {
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
}
