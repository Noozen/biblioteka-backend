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
@Table(name="przedmiot")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class Przedmiot {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;

    private Integer rokWydania;

    @OneToMany(mappedBy = "przedmiot", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<SztukaPrzedmiotu> przedmioty = new LinkedHashSet<>();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="`autor_id`")
    private Autor autor;

    public void addSztukaPrzedmiotu(SztukaPrzedmiotu sztukaPrzedmiotu) {
        sztukaPrzedmiotu.setPrzedmiot(this);
        przedmioty.add(sztukaPrzedmiotu);
    }

    public void removeSztukaPrzedmiotu(SztukaPrzedmiotu sztukaPrzedmiotu) {
        sztukaPrzedmiotu.setPrzedmiot(null);
        przedmioty.remove(sztukaPrzedmiotu);
    }

}
