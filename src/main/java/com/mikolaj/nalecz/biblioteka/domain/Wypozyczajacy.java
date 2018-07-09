package com.mikolaj.nalecz.biblioteka.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Wypozyczajacy extends Osoba {

    private String email;

    @OneToOne(mappedBy = "wypozyczajacy", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Konto konto = new Konto();
}
