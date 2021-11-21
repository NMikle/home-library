package io.mikle.home.lib.core.exception;

import io.mikle.home.lib.api.exception.ErrorCode;

public enum RestErrorCode implements ErrorCode {
    BOOK_NOT_FOUND("BOOK-1");

    private final String code;

    RestErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return code;
    }
}
