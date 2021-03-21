package com.example.adminservice.adminservice.Admin.Repositories;

import com.example.adminservice.adminservice.Admin.Models.State;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface StateRepository extends CrudRepository<State, Integer> {

}