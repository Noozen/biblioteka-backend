package com.mikolaj.nalecz.biblioteka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrzetrzymujacaOsoba {

    private Long id;
    private String imie;
    private String nazwisko;
    private BigDecimal saldo;

    private List<PrzetrzymanyPrzedmiot> przetrzymanePrzedmioty;

}
