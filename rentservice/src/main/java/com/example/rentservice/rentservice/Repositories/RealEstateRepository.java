package com.example.rentservice.rentservice.Repositories;
import com.example.rentservice.rentservice.Models.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import java.util.List;

<<<<<<< HEAD
    public List<RealEstate> findByIsReservatedTrue();
=======
public interface RealEstateRepository extends JpaRepository<RealEstate, Integer> {
    public List<RealEstate> findByIsReservedTrue();
>>>>>>> 18c9e1ddfbfdead1c301a61a13576df62685f594
}