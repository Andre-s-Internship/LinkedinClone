package com.andresinternship.linkedinclone.service;//package com.andresinternship.linkedinclone.service;
//
//import com.andresinternship.linkedinclone.requestDTO.UserLoginRequest;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import java.util.Calendar;
//import java.util.Date;
//
//@Service
//@RestController
//public class JwtTokenService {
//
//    @GetMapping(path = "/generate")
//    public String generateToken(UserLoginRequest userLoginRequest) {
//        Algorithm tokenSigningAlgorithm = Algorithm.HMAC256("test");
//
//        Calendar calendar = Calendar.getInstance();
//        Date issueDate = calendar.getTime();
//        calendar.add(Calendar.MINUTE, 30);
//        Date expiration = calendar.getTime();
//
//        return JWT.create()
//                .withClaim("email", userLoginRequest.getEmail())
//                .withExpiresAt(expiration)
//                .withIssuedAt(issueDate)
//                .sign(tokenSigningAlgorithm);
//    }
//
//    @GetMapping("/getEmail")
//    public String getEmailFromToken(String token) {
//        return JWT.decode(token).getClaim("email").asString();
//    }
//
//}
