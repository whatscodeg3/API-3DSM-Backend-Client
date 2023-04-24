//package com.br.WhatsCodeClientMicroservice.service;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.br.WhatsCodeClientMicroservice.models.User;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//
//@Service
//public class TokenService {
//    public String gerarToken(User user) {
//        return JWT.create()
//                .withIssuer("")
//                .withSubject(user.getUsername())
//                .withClaim("id", user.getId())
//                .withExpiresAt(LocalDateTime.now()
//                        .plusMinutes(10)
//                        .toInstant(ZoneOffset.of("-03:00"))
//                ).sign(Algorithm.HMAC256("secreta"));
//    }
//
//
//    public String getSubject(String token) {
//        return JWT.require(Algorithm.HMAC256("secreta"))
//                .withIssuer("")
//                .build().verify(token).getSubject();
//
//    }
//}
