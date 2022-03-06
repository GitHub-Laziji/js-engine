package org.laziji.commons.js.model.value.primitive;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.OperationException;
import org.laziji.commons.js.model.value.object.JsObject;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;

public class JsNumber extends JsObject {

    //TODO NaN Infinity
    public static JsNumber getNanInstance() {
        JsNumber num = new JsNumber(0);
        num.type = Type.NAN;
        return num;
    }

    private Type type;
    private double value;

    public JsNumber(double value) {
        this.type = Type.NORMAL;
        this.value = value;
    }

    public JsNumber(String value) {
        if (value.isEmpty()) {
            this.type = Type.NORMAL;
            this.value = 0;
        }
        try {
            this.value = Double.parseDouble(value);
            this.type = Type.NORMAL;
        } catch (Exception e) {
            this.type = Type.NAN;
        }
    }

    @Override
    public JsValue getProto() {
        return Top.getThreadLocalTop().getNumberClass().getPrototype();
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
        if(type==Type.NAN){
            return "NaN";
        }
        String v = value + "";
        if (v.endsWith(".0")) {
            return v.substring(0, v.length() - 2);
        }
        return v;
    }

    private enum Type {
        NORMAL,
        NAN,
        INFINITY
    }
}
