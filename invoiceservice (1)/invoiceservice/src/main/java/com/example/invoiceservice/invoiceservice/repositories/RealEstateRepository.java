package com.example.invoiceservice.invoiceservice.repositories;

import com.example.invoiceservice.invoiceservice.models.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateRepository extends JpaRepository<RealEstate, Integer> {
}
