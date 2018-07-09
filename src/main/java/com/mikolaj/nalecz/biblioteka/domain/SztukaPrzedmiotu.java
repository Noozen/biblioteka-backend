package com.mikolaj.nalecz.biblioteka.domain;

import com.mikolaj.nalecz.biblioteka.enums.Lokacja;
import com.mikolaj.nalecz.biblioteka.enums.Stan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="sztuka_przedmiotu")
@Getter
@Setter
@NoArgsConstructor
public class SztukaPrzedmiotu {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Stan stan;

    @Enumerated(value = EnumType.STRING)
    private Lokacja gdzieJest;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="`przedmiot_id`")
    private Przedmiot przedmiot;

    @OneToMany(mappedBy = "sztukaPrzedmiotu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Wypozyczenie> wypozyczenia = new LinkedHashSet<>();

    public void addWypozyczenie(Wypozyczenie wypozyczenie) {
        wypozyczenie.setSztukaPrzedmiotu(this);
        wypozyczenia.add(wypozyczenie);

        wypozyczenie.getOsoba().getWypozyczenia().add(wypozyczenie);
    }

    public void removeWypozyczenie(Wypozyczenie wypozyczenie) {
        wypozyczenie.setSztukaPrzedmiotu(null);
        wypozyczenia.remove(wypozyczenie);

        wypozyczenie.getOsoba().getWypozyczenia().remove(wypozyczenie);
    }
}
