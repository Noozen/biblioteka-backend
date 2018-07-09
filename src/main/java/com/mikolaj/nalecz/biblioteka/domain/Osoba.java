package com.mikolaj.nalecz.biblioteka.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name="osoba")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class Osoba {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imie;
    private String nazwisko;

    @OneToMany(mappedBy = "osoba", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Wypozyczenie> wypozyczenia = new LinkedHashSet<>();

    public void addWypozyczenie(Wypozyczenie wypozyczenie) {
        wypozyczenia.add(wypozyczenie);
        wypozyczenie.setOsoba(this);

        wypozyczenie.getSztukaPrzedmiotu().getWypozyczenia().add(wypozyczenie);
    }

    public void removeWypozyczenie(Wypozyczenie wypozyczenie) {
        wypozyczenia.remove(wypozyczenie);
        wypozyczenie.setOsoba(null);

        wypozyczenie.getSztukaPrzedmiotu().getWypozyczenia().remove(wypozyczenie);
    }

}
