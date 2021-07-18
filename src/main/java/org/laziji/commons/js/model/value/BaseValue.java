package org.laziji.commons.js.model.value;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.OperationException;

public abstract class BaseValue implements Value {

    @Override
    public Value unaryOperation(Token operator) throws OperationException {
        switch (operator) {
            case NON:
                return new BooleanValue(!this.toBoolean().getValue());
            default:
                throw new OperationException("Unrealized.");
        }
    }

    @Override
    public Value binaryOperation(Token operator, Value o) throws OperationException {
        switch (operator) {
            case ADD:
                return new StringValue(toString() + o.toString());
            case OR:
                return new BooleanValue(toBoolean().getValue() || o.toBoolean().getValue());
            case AND:
                return new BooleanValue(toBoolean().getValue() && o.toBoolean().getValue());
            case EQUAL:
                return new BooleanValue(toString().equals(o.toString()));
            case ABS_EQUAL:
                return new BooleanValue(getClass().equals(o.getClass()) && toString().equals(o.toString()));
            case UNEQUAL:
                return new BooleanValue(!binaryOperation(Token.EQUAL, o).toBoolean().getValue());
            case ABS_UNEQUAL:
                return new BooleanValue(!binaryOperation(Token.ABS_EQUAL, o).toBoolean().getValue());
            case GT:
                if (this instanceof NumberValue || o instanceof NumberValue) {
                    try {
                        return new BooleanValue(Double.parseDouble(this.toString()) > Double.parseDouble(o.toString()));
                    } catch (Exception ignored) {
                    }
                }
                return new BooleanValue(this.toString().compareTo(o.toString()) > 0);
            case GT_EQUAL:
                return new BooleanValue(binaryOperation(Token.EQUAL, o).toBoolean().getValue()
                        || binaryOperation(Token.GT, o).toBoolean().getValue());
            case LT:
                if (this instanceof NumberValue || o instanceof NumberValue) {
                    try {
                        return new BooleanValue(Double.parseDouble(this.toString()) < Double.parseDouble(o.toString()));
                    } catch (Exception ignored) {
                    }
                }
                return new BooleanValue(this.toString().compareTo(o.toString()) < 0);
            case LT_EQUAL:
                return new BooleanValue(binaryOperation(Token.EQUAL, o).toBoolean().getValue()
                        || binaryOperation(Token.LT, o).toBoolean().getValue());
            default:
                throw new OperationException("Unrealized.");
        }
    }

    @Override
    public BooleanValue toBoolean() {
        return new BooleanValue(true);
    }

    @Override
    public Value copy() {
        return this;
    }
}
