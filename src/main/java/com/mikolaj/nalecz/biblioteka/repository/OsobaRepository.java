package com.mikolaj.nalecz.biblioteka.repository;

import com.mikolaj.nalecz.biblioteka.domain.Osoba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OsobaRepository extends JpaRepository<Osoba, Long> {

}
