package io.mikle.home.lib.core.exception;

import io.mikle.home.lib.api.exception.ErrorData;

public record PropertyErrorData(String message, int httpStatus) implements ErrorData {
}
