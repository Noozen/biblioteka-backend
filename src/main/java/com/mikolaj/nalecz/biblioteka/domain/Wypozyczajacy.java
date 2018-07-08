package com.mikolaj.nalecz.biblioteka.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Wypozyczajacy extends Osoba {

    @OneToOne(mappedBy = "wypozyczajacy", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Konto konto;

    private Wypozyczajacy() {}

    public Wypozyczajacy(Konto konto) {
        this.konto = konto;
    }
}
