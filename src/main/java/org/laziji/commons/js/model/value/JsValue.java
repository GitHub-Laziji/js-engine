package org.laziji.commons.js.model.value;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.OperationException;

public interface JsValue {

    JsValue binaryOperation(Token operator, JsValue o) throws OperationException;

    JsValue unaryOperation(Token operator) throws OperationException;

    JsBoolean toJsBoolean();

    JsNumber toJsNumber();

    JsString toJsString();

    Boolean toBoolean();

    Double toNumber();

    String toString();

    JsValue copy();
}
