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
                return new StringValue(this.toString() + o.toString());
            case OR:
                return new BooleanValue(this.toBoolean().getValue() || o.toBoolean().getValue());
            case AND:
                return new BooleanValue(this.toBoolean().getValue() && o.toBoolean().getValue());
            case EQUAL:
                return new BooleanValue(this.toString().equals(o.toString()));
            case ABS_EQUAL:
                return new BooleanValue(this.getClass().equals(o.getClass()) && this.toString().equals(o.toString()));
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
