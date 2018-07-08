package com.mikolaj.nalecz.biblioteka.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "konto")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Konto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balans = BigDecimal.ZERO;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wypozyczajacy_id")
    private Wypozyczajacy wypozyczajacy;

    public boolean balansNieUjemny() {
        return balans.compareTo(BigDecimal.ZERO) > -1;
    }

}
