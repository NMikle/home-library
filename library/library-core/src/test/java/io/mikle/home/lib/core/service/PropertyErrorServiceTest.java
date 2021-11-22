package io.mikle.home.lib.core.service;

import io.mikle.home.lib.api.exception.ErrorCode;
import io.mikle.home.lib.api.exception.ErrorData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Locale;

import static java.lang.Integer.parseInt;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(PropertyErrorServiceTest.Config.class)
public class PropertyErrorServiceTest {

    @Value("${error.postfix.message}")
    private String messagePostfix;

    @Value("${error.postfix.code}")
    private String httpStatusPostfix;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private PropertyErrorService testClass;

    @BeforeEach
    void setUp() {
        setField(testClass, "messagePostfix", messagePostfix);
        setField(testClass, "httpStatusPostfix", httpStatusPostfix);
    }

    @Test
    public void retrieve_shouldReturnErrorDataFromMessageSource_whenErrorCodeAndLocaleProvided() {
        System.out.println(httpStatusPostfix);
        final ErrorCode errorCode = mock(ErrorCode.class);
        final List<Object> params = emptyList();
        final Locale locale = Locale.getDefault();
        final String code = "errorCode";
        final String errorMessage = "errorMessage";
        final String statusCode = "400";
        final String messageKey = code + ".message";
        final String statusKey = code + ".httpStatus";

        when(errorCode.code()).thenReturn(code);
        when(messageSource.getMessage(messageKey, params.toArray(), locale)).thenReturn(errorMessage);
        when(messageSource.getMessage(statusKey, params.toArray(), locale)).thenReturn(statusCode);

        final ErrorData result = testClass.retrieve(errorCode, params, locale);

        assertNotNull(result);
        assertNotNull(result.message());
        assertEquals(errorMessage, result.message());
        assertEquals(parseInt(statusCode), result.httpStatus());

        verify(errorCode, times(2)).code();
        verify(messageSource).getMessage(messageKey, params.toArray(), locale);
        verify(messageSource).getMessage(statusKey, params.toArray(), locale);
    }

    @Configuration
    @PropertySource("classpath:test.properties")
    public static class Config {
    }

}
