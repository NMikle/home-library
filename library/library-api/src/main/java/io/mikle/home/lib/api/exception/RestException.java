package io.mikle.home.lib.api.exception;

import io.mikle.home.lib.api.model.ErrorCode;

import java.io.Serial;
import java.util.List;

import static java.util.Collections.emptyList;

public class RestException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4814010160717287418L;

    private final ErrorCode code;
    private final List<Object> params;

    public RestException(ErrorCode code) {
        this(code, emptyList());
    }

    public RestException(ErrorCode code, List<Object> params) {
        this.code = code;
        this.params = params != null && !params.isEmpty() ? params : emptyList();
    }

    public ErrorCode getCode() {
        return code;
    }

    public List<Object> getParams() {
        return params;
    }
}
