package com.andresinternship.linkedinclone.controller;

import com.andresinternship.linkedinclone.controller.dto.LoginResponseDto;
import com.andresinternship.linkedinclone.exceptions.LoginException;
import com.andresinternship.linkedinclone.controller.dto.UserRegistrationRequestDto;
import com.andresinternship.linkedinclone.exceptions.RequestValidationException;
import com.andresinternship.linkedinclone.exceptions.UserAlreadyExistsException;
import com.andresinternship.linkedinclone.controller.dto.UserLoginRequest;
import com.andresinternship.linkedinclone.model.User;
import com.andresinternship.linkedinclone.service.UserService;
import com.andresinternship.linkedinclone.utils.AuthDtoConverter;
import org.springframework.http.HttpHeaders;
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
        try {
            User registeredUser = userService.registerUser(AuthDtoConverter.fromDto(userDTO));

            return new ResponseEntity<>(AuthDtoConverter.toDto(registeredUser), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        } catch (RequestValidationException e) {
            return new ResponseEntity<>("Request validation failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest userDTO) {
        try {
            String jwtToken = userService.login(userDTO.getEmail(), userDTO.getPassword());
            return new ResponseEntity<>(new LoginResponseDto("GOOD", jwtToken), HttpStatus.CREATED);
        } catch (LoginException e) {
            return new ResponseEntity<>("Request validation failed", HttpStatus.BAD_REQUEST);
        }
    }

}
