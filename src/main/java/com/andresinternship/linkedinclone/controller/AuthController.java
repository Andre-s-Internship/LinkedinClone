package com.andresinternship.linkedinclone.controller;

import com.andresinternship.linkedinclone.exceptions.LoginException;
import com.andresinternship.linkedinclone.requestDTO.UserRegistrationRequest;
import com.andresinternship.linkedinclone.exceptions.RequestValidationException;
import com.andresinternship.linkedinclone.exceptions.UserAlreadyExistsException;
import com.andresinternship.linkedinclone.requestDTO.UserLoginRequest;
import com.andresinternship.linkedinclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest userDTO) {
        try {
            userService.registerUser(userDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        } catch (RequestValidationException e) {
            return new ResponseEntity<>("Request validation failed", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest userDTO) {
        try {
            String token = userService.login(userDTO);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            return new ResponseEntity<>("User authenticated successfully!", headers, HttpStatus.CREATED);
        } catch (LoginException e) {
            return new ResponseEntity<>("Request validation failed", HttpStatus.BAD_REQUEST);
        }
    }
}
