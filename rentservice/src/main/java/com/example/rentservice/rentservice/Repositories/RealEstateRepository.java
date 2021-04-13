package com.example.rentservice.rentservice.Repositories;
import com.example.rentservice.rentservice.Models.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RealEstateRepository extends JpaRepository<RealEstate, Integer> {
    public List<RealEstate> findByIsReservedTrue();
}