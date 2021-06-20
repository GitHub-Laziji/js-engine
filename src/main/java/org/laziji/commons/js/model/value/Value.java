package org.laziji.commons.js.model.value;

import org.laziji.commons.js.exception.OperationException;

public interface Value {

    Value add(Value o) throws OperationException;

    Value subtract(Value o) throws OperationException;

    Value multiply(Value o) throws OperationException;

    Value divide(Value o) throws OperationException;

    BooleanValue greater(Value o) throws OperationException;

    BooleanValue greaterOrEqual(Value o) throws OperationException;

    BooleanValue smaller(Value o) throws OperationException;

    BooleanValue smallerOrEqual(Value o) throws OperationException;

    BooleanValue equal(Value o) throws OperationException;

    BooleanValue and(Value o) throws OperationException;

    BooleanValue or(Value o) throws OperationException;

    BooleanValue toBoolean();

}
