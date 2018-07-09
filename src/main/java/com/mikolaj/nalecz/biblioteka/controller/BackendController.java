package com.mikolaj.nalecz.biblioteka.controller;

import com.mikolaj.nalecz.biblioteka.domain.Wypozyczajacy;
import com.mikolaj.nalecz.biblioteka.dto.PrzetrzymanyPrzedmiot;
import com.mikolaj.nalecz.biblioteka.dto.PrzetrzymujacaOsoba;
import com.mikolaj.nalecz.biblioteka.dto.SendEmailRequest;
import com.mikolaj.nalecz.biblioteka.repository.WypozyczajacyRepository;
import com.mikolaj.nalecz.biblioteka.service.EmailComponent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Slf4j
@AllArgsConstructor
public class BackendController {

    private WypozyczajacyRepository wypozyczajacyRepository;
    private EmailComponent emailComponent;

    @RequestMapping(path = "/lateFees")
    @Transactional
    public Flux<PrzetrzymujacaOsoba> lateFees() {
        var osobyZUjemnymBalansem = wypozyczajacyRepository.findByKonto_BalansLessThan(BigDecimal.ZERO);
        var osobyZPretrzymanymiPrzedmiotami =
                osobyZUjemnymBalansem.stream()
                .map(o -> new PrzetrzymujacaOsoba(
                        o.getId(),
                        o.getImie(),
                        o.getNazwisko(),
                        o.getKonto().getBalans(),
                        o.getWypozyczenia()
                                .stream()
                                .filter(w -> w.getDataTeoretycznegoZakonczenia().isBefore(LocalDateTime.now()))
                                .filter(w -> w.getDataFaktycznegoZakonczenia() == null)
                                .map(w -> new PrzetrzymanyPrzedmiot(
                                        w.getSztukaPrzedmiotu().getPrzedmiot().getAutor().getNazwisko(),
                                        w.getSztukaPrzedmiotu().getPrzedmiot().getNazwa(),
                                        w.getDataTeoretycznegoZakonczenia()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());

        return Flux.fromIterable(osobyZPretrzymanymiPrzedmiotami);
    }

    @RequestMapping(path = "/sendLateFeesEmail")
    @Transactional
    public Mono<ResponseEntity> sendLateFeesEmail(@RequestBody SendEmailRequest sendEmailRequest) {
        wypozyczajacyRepository.findById(sendEmailRequest.getId())
                .ifPresent((user) -> emailComponent.wyslijPonaglenie(user.getEmail(), user.getKonto().getBalans().abs()));
        return Mono.just(ResponseEntity.ok().build());
    }

}
