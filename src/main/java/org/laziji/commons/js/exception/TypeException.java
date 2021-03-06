package org.laziji.commons.js.exception;

public class TypeException extends RuntimeException {

    public TypeException() {
        super();
    }

    public TypeException(String message) {
        super(message);
    }

    public TypeException(String format, Object... args) {
        super(String.format(format, args));
    }
}
