package com.example.adminservice.adminservice.Admin;

import com.example.adminservice.adminservice.Admin.RealEstate;
import org.springframework.data.repository.CrudRepository;

import com.example.adminservice.adminservice.Admin.RealEstate;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RealEstateRepository extends CrudRepository<RealEstate, Integer> {

}