package com.example.rentservice.rentservice.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rentservice.rentservice.Models.State;

public interface StateRepository extends JpaRepository<State, Integer> {

}