package io.mikle.home.lib.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageConfig {

    @Value("${default.encoding}")
    private String defaultEncoding;

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setDefaultEncoding(defaultEncoding);
        source.setBasename("classpath:errorCodes");
        return source;
    }

}
