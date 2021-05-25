package com.example.adminservice.adminservice.Admin.Repositories;

import com.example.adminservice.adminservice.Admin.Models.RealEstate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RealEstateRepository extends JpaRepository<RealEstate, Integer> {
    Page<RealEstate> findByCityContainingIgnoreCase(String city, Pageable pageable);

    @Query("SELECT r FROM RealEstate r WHERE (r.isReservated = FALSE) AND (r.name LIKE %?1%"
            + " OR r.address LIKE %?1%"
            + " OR r.country LIKE %?1%"
            + " OR r.city LIKE %?1%"
            + " OR r.description LIKE %?1%"
            + " OR CONCAT(r.price, '') LIKE %?1%)")
    Page<RealEstate> search(String keyword, Pageable pageable);

    Page<RealEstate> findByIsReservatedFalse(Pageable pageable);
}