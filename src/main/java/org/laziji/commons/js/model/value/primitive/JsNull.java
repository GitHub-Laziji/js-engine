package org.laziji.commons.js.model.value.primitive;

import org.laziji.commons.js.exception.ReferenceException;
import org.laziji.commons.js.model.value.object.JsObject;
import org.laziji.commons.js.model.value.JsValue;

public class JsNull extends JsObject {

    private static final JsNull instance = new JsNull();

    public JsNull() {
    }

    public static JsNull getInstance() {
        return instance;
    }

    @Override
    public JsValue addProperty(String key, JsValue value, JsObject.ObjectPropertyType type) {
        throw new ReferenceException("Cannot read properties of null (reading '%s')", key);
    }

    @Override
    public JsValue getProperty(String key) {
        throw new ReferenceException("Cannot read properties of null (reading '%s')", key);
    }

    @Override
    public JsBoolean toJsBoolean() {
        return JsBoolean.getFalseInstance();
    }

    @Override
    public JsString toJsString() {
        return new JsString("null");
    }

    @Override
    public JsNumber toJsNumber() {
        return new JsNumber(0);
    }
}
