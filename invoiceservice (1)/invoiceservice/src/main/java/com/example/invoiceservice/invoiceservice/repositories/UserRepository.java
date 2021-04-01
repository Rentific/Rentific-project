package com.example.invoiceservice.invoiceservice.repositories;

import com.example.invoiceservice.invoiceservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
