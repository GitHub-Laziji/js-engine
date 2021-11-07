package org.laziji.commons.js.model.value;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.OperationException;
import org.laziji.commons.js.model.value.primitive.JsString;

public abstract class BaseJsValue implements JsValue {

    @Override
    public JsValue unaryOperation(Token operator) throws OperationException {
        switch (operator) {
            case NON:
                return new JsBoolean(!this.toBoolean());
            default:
                throw new OperationException("Unrealized.");
        }
    }

    @Override
    public JsValue binaryOperation(Token operator, JsValue o) throws OperationException {
        switch (operator) {
            case ADD:
                return new JsStringObject(toString() + o.toString());
            case OR:
                return new JsBoolean(toBoolean() || o.toBoolean());
            case AND:
                return new JsBoolean(toBoolean() && o.toBoolean());
            case EQUAL:
                return new JsBoolean(toString().equals(o.toString()));
            case ABS_EQUAL:
                return new JsBoolean(getClass().equals(o.getClass()) && toString().equals(o.toString()));
            case UNEQUAL:
                return new JsBoolean(!binaryOperation(Token.EQUAL, o).toBoolean());
            case ABS_UNEQUAL:
                return new JsBoolean(!binaryOperation(Token.ABS_EQUAL, o).toBoolean());
            case GT:
                if (this instanceof JsNumber || o instanceof JsNumber) {
                    try {
                        return new JsBoolean(Double.parseDouble(this.toString()) > Double.parseDouble(o.toString()));
                    } catch (Exception ignored) {
                    }
                }
                return new JsBoolean(this.toString().compareTo(o.toString()) > 0);
            case GT_EQUAL:
                return new JsBoolean(binaryOperation(Token.EQUAL, o).toBoolean()
                        || binaryOperation(Token.GT, o).toBoolean());
            case LT:
                if (this instanceof JsNumber || o instanceof JsNumber) {
                    try {
                        return new JsBoolean(Double.parseDouble(this.toString()) < Double.parseDouble(o.toString()));
                    } catch (Exception ignored) {
                    }
                }
                return new JsBoolean(this.toString().compareTo(o.toString()) < 0);
            case LT_EQUAL:
                return new JsBoolean(binaryOperation(Token.EQUAL, o).toBoolean()
                        || binaryOperation(Token.LT, o).toBoolean());
            default:
                throw new OperationException("Unrealized.");
        }
    }

    @Override
    public JsBoolean toJsBoolean() {
        return new JsBoolean(true);
    }

    @Override
    public JsNumber toJsNumber() {
        throw new RuntimeException();
    }

    @Override
    public JsString toJsString() {
        return new JsString(toString());
    }

    @Override
    public Boolean toBoolean() {
        return toJsBoolean().getValue();
    }

    @Override
    public Double toNumber() {
        return toJsNumber().getValue();
    }

    @Override
    public JsValue copy() {
        return this;
    }
}
