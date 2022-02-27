package org.laziji.commons.js.model.value.object;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.OperationException;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.primitive.JsBoolean;
import org.laziji.commons.js.model.value.primitive.JsNumber;

public class JsNumberObject extends JsObject {

    private final JsNumber primitiveValue;
    
    public JsNumberObject(double value) {
        primitiveValue = new JsNumber(value);
    }

    @Override
    public JsValue getProto() {
        return Top.getThreadLocalTop().getNumberClass().getPrototype();
    }

    @Override
    public JsValue binaryOperation(Token operator, JsValue o) throws OperationException {
        return primitiveValue.binaryOperation(operator, o);
    }

    @Override
    public JsBoolean toJsBoolean() {
        return primitiveValue.toJsBoolean();
    }

    @Override
    public JsNumber toJsNumber() {
        return primitiveValue;
    }

    @Override
    public String toString() {
        return primitiveValue.toString();
    }
}
