package io.mikle.home.lib.api.service;

import io.mikle.home.lib.api.exception.ErrorCode;
import io.mikle.home.lib.api.exception.ErrorData;

import java.util.List;
import java.util.Locale;

public interface ErrorService {

    ErrorData retrieve(ErrorCode code, List<Object> params, Locale locale);

}
