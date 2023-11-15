package com.andresinternship.linkedinclone.service;

import com.andresinternship.linkedinclone.service.helper.TokenGenerator;
import com.andresinternship.linkedinclone.controller.requestdto.UserLoginRequest;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;

@Service
public class TokenService {

    @GetMapping(path = "/generate")
    public String generateToken(@RequestBody UserLoginRequest userLoginRequest) {
        Calendar calendar = Calendar.getInstance();
        Date issueDate = calendar.getTime();
        calendar.add(Calendar.MINUTE, 30);
        Date expiration = calendar.getTime();

        return TokenGenerator.create()
                .withAttribute("email", userLoginRequest.getEmail())
                .withIssuedAt(issueDate)
                .withExpiresAt(expiration)
                .build();
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
        LinkedHashMap json;
        try {
            json = (LinkedHashMap) parser.parse();
            return (String) json.get("email");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

}
