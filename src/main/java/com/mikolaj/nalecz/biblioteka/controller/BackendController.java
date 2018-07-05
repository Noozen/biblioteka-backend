package com.mikolaj.nalecz.biblioteka.controller;

import com.mikolaj.nalecz.biblioteka.domain.User;
import com.mikolaj.nalecz.biblioteka.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
@Slf4j
public class BackendController {

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/hello")
    public Mono<String> sayHello() {
        log.info("GET called on /hello resource");
        return Mono.just(HELLO_TEXT);
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Long> addNewUser (@ModelAttribute(name = "firstName") String firstName, @ModelAttribute(name = "lastName") String lastName) {
        User user = new User(firstName, lastName);
//        user = userRepository.save(user);
        log.info(user.toString() + " successfully saved into DB");

        return Mono.fromCallable(() -> userRepository.save(user)).map(User::getId);
    }

    @GetMapping(path="/user/{id}")
    public Mono<User> getUserById(@PathVariable("id") long id) {
        log.info("Reading user with id " + id + " from database.");
        return Mono.just(userRepository.findById(id).get());
    }

}
