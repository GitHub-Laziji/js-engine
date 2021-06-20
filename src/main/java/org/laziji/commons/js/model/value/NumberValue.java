package org.laziji.commons.js.model.value;

import org.laziji.commons.js.exception.OperationException;

public class NumberValue extends BaseValue {

    private int value;

    public NumberValue(String value) {
        this.value = Integer.parseInt(value.trim());
    }

    public NumberValue(Integer value) {
        this.value = value;
    }


    @Override
    public Value add(Value o) throws OperationException {
        if (o instanceof NumberValue) {
            this.value += ((NumberValue) o).getValue();
            return this;
        }
        throw new OperationException();
    }

    @Override
    public Value subtract(Value o) throws OperationException {
        if (o instanceof NumberValue) {
            this.value -= ((NumberValue) o).getValue();
            return this;
        }
        throw new OperationException();
    }

    @Override
    public Value multiply(Value o) throws OperationException {
        if (o instanceof NumberValue) {
            this.value *= ((NumberValue) o).getValue();
            return this;
        }
        throw new OperationException();
    }

    @Override
    public Value divide(Value o) throws OperationException {
        if (o instanceof NumberValue) {
            this.value /= ((NumberValue) o).getValue();
            return this;
        }
        throw new OperationException();
    }

    @Override
    public BooleanValue greater(Value o) throws OperationException {
        if (o instanceof NumberValue) {
            return this.value > ((NumberValue) o).getValue() ?
                    BooleanValue.getTrueInstance() :
                    BooleanValue.getFalseInstance();
        }
        throw new OperationException();
    }

    @Override
    public BooleanValue greaterOrEqual(Value o) throws OperationException {
        if (o instanceof NumberValue) {
            return this.value >= ((NumberValue) o).getValue() ?
                    BooleanValue.getTrueInstance() :
                    BooleanValue.getFalseInstance();
        }
        throw new OperationException();
    }

    @Override
    public BooleanValue smaller(Value o) throws OperationException {
        if (o instanceof NumberValue) {
            return this.value < ((NumberValue) o).getValue() ?
                    BooleanValue.getTrueInstance() :
                    BooleanValue.getFalseInstance();
        }
        throw new OperationException();
    }

    @Override
    public BooleanValue smallerOrEqual(Value o) throws OperationException {
        if (o instanceof NumberValue) {
            return this.value <= ((NumberValue) o).getValue() ?
                    BooleanValue.getTrueInstance() :
                    BooleanValue.getFalseInstance();
        }
        throw new OperationException();
    }

    @Override
    public BooleanValue equal(Value o) throws OperationException {
        if (o instanceof NumberValue) {
            return this.value == ((NumberValue) o).getValue() ?
                    BooleanValue.getTrueInstance() :
                    BooleanValue.getFalseInstance();
        }
        throw new OperationException();
    }

    @Override
    public BooleanValue toBoolean() {
        return value == 0 ? BooleanValue.getFalseInstance() : BooleanValue.getTrueInstance();
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
