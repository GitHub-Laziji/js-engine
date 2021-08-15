package org.laziji.commons.js.model.value;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.OperationException;

public interface Value {

    Value binaryOperation(Token operator, Value o) throws OperationException;

    Value unaryOperation(Token operator) throws OperationException;

    BooleanValue toBooleanValue();

    NumberValue toNumberValue();

    Boolean toBoolean();

    Double toNumber();

    Value copy();
}
