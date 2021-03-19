package com.example.rentservice.rentservice.Repositories;
import org.springframework.data.repository.CrudRepository;
import com.example.rentservice.rentservice.Models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}