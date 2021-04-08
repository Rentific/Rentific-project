package com.example.invoiceservice.invoiceservice.repositories;

import com.example.invoiceservice.invoiceservice.models.RealEstate;
<<<<<<< HEAD
import com.example.invoiceservice.invoiceservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RealEstateRepository extends JpaRepository<RealEstate, Integer> {
    Optional<RealEstate> findRealEstateByName(String name);
=======
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateRepository extends JpaRepository<RealEstate, Integer> {
>>>>>>> fbe799c4da015c97988314d0f6271c80e6bc18c9
}
