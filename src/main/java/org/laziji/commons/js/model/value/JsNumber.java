package org.laziji.commons.js.model.value;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.OperationException;
import org.laziji.commons.js.model.value.env.Top;

public class JsNumber extends JsObject {

    private double value;

    public JsNumber(double value) {
        this.value = value;
    }

    @Override
    public JsValue getProto() {
        return Top.getNumberClass().getPrototype();
    }

    @Override
    public JsValue binaryOperation(Token operator, JsValue o) throws OperationException {
        if (!(o instanceof JsNumber)) {
            return super.binaryOperation(operator, o);
        }
        JsNumber b = (JsNumber) o;
        switch (operator) {
            case ADD:
                return new JsNumber(value + b.getValue());
            case SUB:
                return new JsNumber(value - b.getValue());
            case MUL:
                return new JsNumber(value * b.getValue());
            case DIV:
                return new JsNumber(value / b.getValue());
            default:
                return super.binaryOperation(operator, o);
        }
    }

    @Override
    public JsBoolean toJsBoolean() {
        return new JsBoolean(value == 0);
    }

    @Override
    public JsNumber toJsNumber() {
        return this;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        String v = value + "";
        if (v.endsWith(".0")) {
            return v.substring(0, v.length() - 2);
        }
        return v;
    }
}
