package com.example.backend.service;

import com.example.backend.entity.Social;
import com.example.backend.entity.User;
import com.example.backend.repository.SocialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SocialService {

    private final SocialRepository repository;

    public SocialService(SocialRepository repository) {
        this.repository = repository;
    }

    public Optional<Social> findByUser(User user) {

         return repository.findByUser(user);
    }

    public Social create(User user,String facebook,String line ,String instagram, String tiktok){
       //TODO:Valodate


        //create
        Social entity = new Social();
        entity.setUser(user);
        entity.setFaceook(facebook);
        entity.setLine(line);
        entity.setInstagram(instagram);
        entity.setTiktok(tiktok);
        entity.setTiktok(tiktok);

        return repository.save(entity);
    }

}
