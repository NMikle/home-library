package io.mikle.home.lib.core.exception;

import io.mikle.home.lib.api.exception.ErrorCode;
import io.mikle.home.lib.api.exception.ErrorData;
import io.mikle.home.lib.api.exception.RestException;
import io.mikle.home.lib.api.service.ErrorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestExceptionHandlerTest {

    @Mock
    private ErrorService errorService;

    @InjectMocks
    private RestExceptionHandler testClass;

    @Test
    public void handleRestException_shouldReturnResponseEntityWithCorrectData_whenExceptionCaught() {
        final String errorMessage = "errorMessage";
        final int httpStatus = 400;
        final List<Object> params = List.of(3);
        final Locale locale = Locale.getDefault();
        final ErrorCode errorCode = mock(ErrorCode.class);
        final ErrorData errorData = mock(ErrorData.class);
        final RestException exception = new RestException(errorCode, params);

        when(errorService.retrieve(errorCode, params, locale)).thenReturn(errorData);
        when(errorData.message()).thenReturn(errorMessage);
        when(errorData.httpStatus()).thenReturn(httpStatus);

        var actualResult = testClass.handleRestException(exception, locale);

        assertNotNull(actualResult);
        assertNotNull(actualResult.getBody());
        assertEquals(errorMessage, actualResult.getBody().message());
        assertEquals(httpStatus, actualResult.getStatusCode().value());

        verify(errorService).retrieve(errorCode, params, locale);
    }

}
