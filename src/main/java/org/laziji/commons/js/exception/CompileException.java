package org.laziji.commons.js.exception;

public class CompileException extends Exception {

    public CompileException() {
        super();
    }

    public CompileException(String format, Object... args) {
        super(String.format(format, args));
    }
}
