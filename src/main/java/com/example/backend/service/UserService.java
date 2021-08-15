package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.exception.UserException;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(String email, String password, String name) throws BaseException {

        //validate
        if (Objects.isNull(email)) {
            //throw error email null
            throw UserException.createEmailNull();
        }
        if (Objects.isNull(password)) {
            //throw error password null
            throw UserException.createPasswordNull();
        }
        if (Objects.isNull(name)) {
            //throw error name null
            throw UserException.createNameNull();
        }

        //varify
        if (repository.existsByEmail(email)) {
            throw UserException.createEmailDuplocated();
        }


        //save
        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setName(name);

        return repository.save(entity);
    }
}
