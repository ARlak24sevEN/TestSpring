package com.example.backend.repository;

import com.example.backend.entity.Address;
import com.example.backend.entity.Social;
import com.example.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//User ref entity , String is id is a primary key
public interface AddressRepository extends CrudRepository<Address, String> {

    //return
    //Optional fined option not null and not find option null
    List<Address> findByUser(User user);
}
