package org.laziji.commons.js.model.value;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.OperationException;

public class NumberValue extends ObjectValue {

    private double value;

    public NumberValue(double value) {
        this.value = value;
    }

    @Override
    public Value binaryOperation(Token operator, Value o) throws OperationException {
        if (!(o instanceof NumberValue)) {
            return super.binaryOperation(operator, o);
        }
        NumberValue b = (NumberValue) o;
        switch (operator) {
            case ADD:
                return new NumberValue(value + b.getValue());
            case SUB:
                return new NumberValue(value - b.getValue());
            case MUL:
                return new NumberValue(value * b.getValue());
            case DIV:
                return new NumberValue(value / b.getValue());
            default:
                return super.binaryOperation(operator, o);
        }
    }

    @Override
    public BooleanValue toBoolean() {
        return new BooleanValue(value == 0);
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
