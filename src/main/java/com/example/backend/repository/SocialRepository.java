package com.example.backend.repository;

import com.example.backend.entity.Social;
import com.example.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//User ref entity , String is id is a primary key
public interface SocialRepository extends CrudRepository<Social, String> {

    //return
    //Optional fined option not null and not find option null
    Optional<Social> findByUser(User user);
}
