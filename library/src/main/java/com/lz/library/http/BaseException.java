package com.lz.library.http;

public class BaseException extends RuntimeException {

    private int errorCode = HttpCode.UNKNOWN_CODE;

    public BaseException() {
    }

    public BaseException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
