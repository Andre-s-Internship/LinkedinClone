package com.andresinternship.linkedinclone.controller;

import com.andresinternship.linkedinclone.controller.dto.response.UserLoginResponseDto;
import com.andresinternship.linkedinclone.exceptions.AuthException;
import com.andresinternship.linkedinclone.controller.dto.request.UserRegistrationRequestDto;
import com.andresinternship.linkedinclone.controller.dto.request.UserLoginRequest;
import com.andresinternship.linkedinclone.model.User;
import com.andresinternship.linkedinclone.service.UserService;
import com.andresinternship.linkedinclone.utils.AuthDtoConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequestDto userDTO) {
        User registeredUser = userService.registerUser(AuthDtoConverter.fromDto(userDTO));
        return new ResponseEntity<>(AuthDtoConverter.toDto(registeredUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest userDTO) {
        try {
            String jwtToken = userService.login(userDTO.getEmail(), userDTO.getPassword());
            return new ResponseEntity<>(new UserLoginResponseDto("Login Successful", jwtToken), HttpStatus.CREATED);
        } catch (AuthException e) {
            return new ResponseEntity<>("Request validation failed", HttpStatus.BAD_REQUEST);
        }
    }

}
