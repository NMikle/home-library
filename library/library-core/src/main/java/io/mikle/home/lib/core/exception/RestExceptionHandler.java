package io.mikle.home.lib.core.exception;

import io.mikle.home.lib.api.exception.ErrorData;
import io.mikle.home.lib.api.exception.ErrorResponse;
import io.mikle.home.lib.api.exception.RestException;
import io.mikle.home.lib.api.service.ErrorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Locale;

@RestControllerAdvice
public record RestExceptionHandler(ErrorService errorService) {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorResponse> handleRestException(RestException e, Locale locale) {
        final ErrorData data = errorService.retrieve(e.getCode(), e.getParams(), locale);
        return ResponseEntity.status(data.httpStatus())
                .body(createErrorResponse(data));
    }

    private ErrorResponse createErrorResponse(ErrorData data) {
        return new ErrorResponse(data.message(), LocalDateTime.now());
    }

}
