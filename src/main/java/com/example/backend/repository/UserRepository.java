package com.example.backend.repository;

import com.example.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//User ref entity , String is id is a primary key
public interface UserRepository extends CrudRepository<User, String> {

    //return
    //Optional fined option not null and not find option null
    Optional<User> findByEmail(String email);

    //check same email
    boolean existsByEmail(String email);
}
