package com.bwpsoft.studyframwork.utils.exception;

/**
 * 自定义异常
 */
public class MyRuntimeException extends Exception {
    public MyRuntimeException() {

    }

    public MyRuntimeException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public MyRuntimeException(String exceptionMessage, Throwable throwable) {
        super(exceptionMessage, throwable);
    }

    public MyRuntimeException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        if (super.getMessage() == null) {
            return "Unknown Exception";
        }
        return super.getMessage();
    }
}
