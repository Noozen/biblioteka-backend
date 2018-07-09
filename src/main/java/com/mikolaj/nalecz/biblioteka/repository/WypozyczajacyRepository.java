package com.mikolaj.nalecz.biblioteka.repository;

import com.mikolaj.nalecz.biblioteka.domain.Osoba;
import com.mikolaj.nalecz.biblioteka.domain.Wypozyczajacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface WypozyczajacyRepository extends JpaRepository<Wypozyczajacy, Long> {

    List<Wypozyczajacy> findByKonto_BalansLessThan(BigDecimal maximumBalance);

}
