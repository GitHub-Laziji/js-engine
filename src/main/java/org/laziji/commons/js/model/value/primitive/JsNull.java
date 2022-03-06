package org.laziji.commons.js.model.value.primitive;

import org.laziji.commons.js.exception.ReferenceException;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.object.JsObject;

public class JsNull extends JsObject {

    public JsNull() {
    }

    public static JsNull getInstance() {
        return new JsNull();
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
