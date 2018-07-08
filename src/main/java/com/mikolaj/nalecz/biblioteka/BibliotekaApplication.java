package com.mikolaj.nalecz.biblioteka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@Slf4j
public class BibliotekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliotekaApplication.class, args);
    }
}
