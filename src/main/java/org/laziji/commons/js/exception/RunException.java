package org.laziji.commons.js.exception;

public class RunException extends Exception {

    public RunException() {
        super();
    }

    public RunException(String message) {
        super(message);
    }

    public RunException(String format, Object... args) {
        super(String.format(format, args));
    }
}
