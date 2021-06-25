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
