package com.example.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {
    @Value("${app.token.secret}")
    private String secret;//=>ควรเปลี่ยนทุกๆ ที่เดือนเพื่อป้องกันการสุ้มเจอ

    @Value("${app.token.issuer}")
    private String issuer;//=>บอกด้วยว่าใครเป็นคนสร้าง Service

    public String tokenize(User user) {

        Calendar calendar = Calendar.getInstance(); //=>Get time now
        calendar.add(Calendar.MINUTE,60); //Get time +60 minute
        Date expiresAt = calendar.getTime();

        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal",user.getId())
                .withClaim("role","USER")
                .withExpiresAt(expiresAt)
                .sign(algorithm());
    }

    //verify my token
    public  DecodedJWT verify(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build();// result verify instance
            return verifier.verify(token);
        }catch (Exception e){
            return null;
        }

    }

    private Algorithm algorithm(){
        return Algorithm.HMAC256(secret);
    }
}
