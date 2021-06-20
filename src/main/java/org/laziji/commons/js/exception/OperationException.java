package org.laziji.commons.js.exception;

public class OperationException extends Exception {

    public OperationException() {
        super();
    }

    public OperationException(String format, Object... args) {
        super(String.format(format, args));
    }
}
