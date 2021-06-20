package org.laziji.commons.js.exception;

public class ReferenceException extends Exception {

    public ReferenceException() {
        super();
    }

    public ReferenceException(String message) {
        super(message);
    }

    public ReferenceException(String format, Object... args) {
        super(String.format(format, args));
    }
}
