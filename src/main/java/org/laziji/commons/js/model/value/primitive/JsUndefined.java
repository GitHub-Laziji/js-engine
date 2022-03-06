package org.laziji.commons.js.model.value.primitive;

import org.laziji.commons.js.exception.TypeException;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.object.JsObject;

public class JsUndefined extends JsObject {

    public JsUndefined() {
    }

    public static JsUndefined getInstance() {
        return new JsUndefined();
    }

    @Override
    public JsValue addProperty(String key, JsValue value, JsObject.ObjectPropertyType type) {
        throw new TypeException("Cannot read properties of undefined (reading '%s')", key);
    }

    @Override
    public JsValue getProperty(String key) {
        throw new TypeException("Cannot read properties of undefined (reading '%s')", key);
    }

    @Override
    public JsBoolean toJsBoolean() {
        return JsBoolean.getFalseInstance();
    }

    @Override
    public JsString toJsString() {
        return new JsString("undefined");
    }

    @Override
    public JsNumber toJsNumber() {
        // Number(undefined) == NaN
        return new JsNumber(0);
    }

}
