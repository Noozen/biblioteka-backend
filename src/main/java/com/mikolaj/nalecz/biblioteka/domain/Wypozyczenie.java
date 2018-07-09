package com.mikolaj.nalecz.biblioteka.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="wypozyczenie")
@Getter
@Setter
@NoArgsConstructor
public class Wypozyczenie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataStartu;
    private LocalDateTime dataTeoretycznegoZakonczenia;
    private LocalDateTime dataFaktycznegoZakonczenia;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="`user_id`")
    private Osoba osoba;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="`sztukaprzedmiotu_id`")
    private SztukaPrzedmiotu sztukaPrzedmiotu;

}
