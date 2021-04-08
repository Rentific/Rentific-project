package com.example.invoiceservice.invoiceservice.repositories;

import com.example.invoiceservice.invoiceservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

=======

public interface UserRepository extends JpaRepository<User, Integer> {
>>>>>>> fbe799c4da015c97988314d0f6271c80e6bc18c9
}
