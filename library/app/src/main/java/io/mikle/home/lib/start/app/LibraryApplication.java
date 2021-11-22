package io.mikle.home.lib.start.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "io.mikle.home.lib")
public class LibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class);
    }
}
