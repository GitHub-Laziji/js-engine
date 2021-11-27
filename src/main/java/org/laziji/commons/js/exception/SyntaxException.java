package org.laziji.commons.js.exception;

public class SyntaxException extends RuntimeException {

    public SyntaxException() {
        super();
    }

    public SyntaxException(String message) {
        super(message);
    }

    public SyntaxException(String format, Object... args) {
        super(String.format(format, args));
    }
}
