package com.example.rentservice.rentservice.Repositories;
import com.example.rentservice.rentservice.Models.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RealEstateRepository extends JpaRepository<RealEstate, Integer> {

    public List<RealEstate> findByIsReservatedTrue();
}