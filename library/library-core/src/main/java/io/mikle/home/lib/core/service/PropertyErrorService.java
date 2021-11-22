package io.mikle.home.lib.core.service;

import io.mikle.home.lib.api.exception.ErrorCode;
import io.mikle.home.lib.api.exception.ErrorData;
import io.mikle.home.lib.api.service.ErrorService;
import io.mikle.home.lib.core.exception.PropertyErrorData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public record PropertyErrorService(MessageSource messageSource,
                                   @Value("${error.postfix.message}") String messagePostfix,
                                   @Value("${error.postfix.code}") String httpStatusPostfix) implements ErrorService {

    private static final String DOT = ".";

    @Override
    public ErrorData retrieve(ErrorCode code, List<Object> params, Locale locale) {
        final String message = messageSource.getMessage(extractMessageKey(code), params.toArray(), locale);
        final String httpStatus = messageSource.getMessage(extractHttpStatusKey(code), params.toArray(), locale);
        return new PropertyErrorData(message, Integer.parseInt(httpStatus));
    }

    private String extractMessageKey(ErrorCode code) {
        return code.code() + DOT + messagePostfix;
    }

    private String extractHttpStatusKey(ErrorCode code) {
        return code.code() + DOT + httpStatusPostfix;
    }
}
