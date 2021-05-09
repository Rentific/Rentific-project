package com.example.invoiceservice.invoiceservice.repositories;

import com.example.invoiceservice.invoiceservice.models.RealEstate;

import com.example.invoiceservice.invoiceservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RealEstateRepository extends JpaRepository<RealEstate, Integer> {
    Optional<RealEstate> findRealEstateByName(String name);

}
