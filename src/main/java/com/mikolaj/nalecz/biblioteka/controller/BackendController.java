package com.mikolaj.nalecz.biblioteka.controller;

import com.mikolaj.nalecz.biblioteka.domain.Konto;
import com.mikolaj.nalecz.biblioteka.domain.Osoba;
import com.mikolaj.nalecz.biblioteka.domain.Wypozyczajacy;
import com.mikolaj.nalecz.biblioteka.repository.OsobaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@Slf4j
public class BackendController {

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

    @Autowired
    private OsobaRepository osobaRepository;

    @RequestMapping(path = "/hello")
    public Mono<String> sayHello() {
        log.info("GET called on /hello resource");
        return Mono.just(HELLO_TEXT);
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Long> addNewUser (@RequestBody MultiValueMap<String, String> params) {
        Wypozyczajacy wypozyczajacy = new Wypozyczajacy(new Konto());
        osobaRepository.save(wypozyczajacy);
        return Mono.just(1L);
    }

    @GetMapping(path="/user/{id}")
    public Mono<Osoba> getUserById(@PathVariable("id") long id) {
        log.info("Reading osoba with id " + id + " from database.");
        return Mono.just(osobaRepository.findById(id).get());
    }

}
