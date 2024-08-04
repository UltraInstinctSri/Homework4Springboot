package com.Homework.Homework4.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Currency")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CurrencyEntity extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromCurrency;
    private String toCurrency;
    private double units;
    private double convertedCurrency;
}
