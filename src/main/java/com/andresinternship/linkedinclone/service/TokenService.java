package com.andresinternship.linkedinclone.service;

import com.andresinternship.linkedinclone.model.CustomJWTToken;
import com.andresinternship.linkedinclone.model.TokenGenerator;
import com.andresinternship.linkedinclone.requestDTO.UserLoginRequest;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@RestController
public class TokenService {

    @GetMapping(path = "/generate")
    public String generateToken(@RequestBody UserLoginRequest userLoginRequest) {
        Calendar calendar = Calendar.getInstance();
        Date issueDate = calendar.getTime();
        calendar.add(Calendar.MINUTE, 30);
        Date expiration = calendar.getTime();

        return TokenGenerator.create()
                .withAttribute("email", userLoginRequest.getEmail())
                .withExpiresAt(expiration)
                .withIssuedAt(issueDate)
                .build();
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestBody String token) {
        String[] parts = token.split("\\.");
        CustomJWTToken customJWTToken = TokenGenerator.create(parts[0], parts[1]);
        boolean values = Objects.equals(token, customJWTToken.toString());
        Date expiration = extractExpirationDateFromToken(token);
        return values && expiration.compareTo(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())) > 0;
    }

    @GetMapping("/decode")
    public String decodeToken(@RequestBody String token) {
        String[] parts = token.split("\\.");
        System.out.println(Arrays.toString(parts));
        byte[] decodedHeader = Base64.getDecoder().decode(parts[0]);
        byte[] decodedPayload = Base64.getDecoder().decode(parts[1]);
        return "Header: " + new String(decodedHeader) + "\n"
                + "Payload: " + new String(decodedPayload) + "\n";
    }

    @GetMapping("/extractEmail")
    public String extractEmailFromToken(@RequestBody String token) {
        String[] parts = token.split("\\.");
        String jsonString = new String(Base64.getDecoder().decode(parts[1]));
        JSONParser parser = new JSONParser(jsonString);
        LinkedHashMap json = null;
        try {
            json = (LinkedHashMap) parser.parse();
            return (String) json.get("email");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    @GetMapping("/extractDate")
    public Date extractExpirationDateFromToken(@RequestBody String token)  {
        String[] parts = token.split("\\.");
        String jsonString = new String(Base64.getDecoder().decode(parts[1]));
        JSONParser parser = new JSONParser(jsonString);
        LinkedHashMap json;
        Date expiration = null;
        try {
            json = (LinkedHashMap) parser.parse();
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            expiration = dateFormat.parse((String) json.get("expires_at"));
        } catch (ParseException | java.text.ParseException u) {
            u.getMessage();
        }

        return expiration;
    }
}
