package org.laziji.commons.js.model.value;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.OperationException;

public abstract class BaseValue implements Value {

    @Override
    public Value unaryOperation(Token operator) throws OperationException {
        switch (operator) {
            case NON:
                return new BooleanValue(!this.toBoolean());
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
                return new BooleanValue(toBoolean() || o.toBoolean());
            case AND:
                return new BooleanValue(toBoolean() && o.toBoolean());
            case EQUAL:
                return new BooleanValue(toString().equals(o.toString()));
            case ABS_EQUAL:
                return new BooleanValue(getClass().equals(o.getClass()) && toString().equals(o.toString()));
            case UNEQUAL:
                return new BooleanValue(!binaryOperation(Token.EQUAL, o).toBoolean());
            case ABS_UNEQUAL:
                return new BooleanValue(!binaryOperation(Token.ABS_EQUAL, o).toBoolean());
            case GT:
                if (this instanceof NumberValue || o instanceof NumberValue) {
                    try {
                        return new BooleanValue(Double.parseDouble(this.toString()) > Double.parseDouble(o.toString()));
                    } catch (Exception ignored) {
                    }
                }
                return new BooleanValue(this.toString().compareTo(o.toString()) > 0);
            case GT_EQUAL:
                return new BooleanValue(binaryOperation(Token.EQUAL, o).toBoolean()
                        || binaryOperation(Token.GT, o).toBoolean());
            case LT:
                if (this instanceof NumberValue || o instanceof NumberValue) {
                    try {
                        return new BooleanValue(Double.parseDouble(this.toString()) < Double.parseDouble(o.toString()));
                    } catch (Exception ignored) {
                    }
                }
                return new BooleanValue(this.toString().compareTo(o.toString()) < 0);
            case LT_EQUAL:
                return new BooleanValue(binaryOperation(Token.EQUAL, o).toBoolean()
                        || binaryOperation(Token.LT, o).toBoolean());
            default:
                throw new OperationException("Unrealized.");
        }
    }

    @Override
    public BooleanValue toBooleanValue() {
        return new BooleanValue(true);
    }

    @Override
    public NumberValue toNumberValue() {
        throw new RuntimeException();
    }

    @Override
    public Boolean toBoolean() {
        return toBooleanValue().getValue();
    }

    @Override
    public Double toNumber() {
        return toNumberValue().getValue();
    }

    @Override
    public Value copy() {
        return this;
    }
}
