package com.Homework.Homework4.repositories;

import com.Homework.Homework4.entities.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepo extends JpaRepository<CurrencyEntity, Long> {
}
