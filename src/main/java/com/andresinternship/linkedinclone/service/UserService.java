package com.andresinternship.linkedinclone.service;

import com.andresinternship.linkedinclone.exceptions.LoginException;
import com.andresinternship.linkedinclone.controller.requestdto.UserRegistrationRequest;
import com.andresinternship.linkedinclone.exceptions.UserAlreadyExistsException;
import com.andresinternship.linkedinclone.controller.requestdto.UserLoginRequest;
import com.andresinternship.linkedinclone.model.User;
import com.andresinternship.linkedinclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationRequest userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User newUser = new User();
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(encodePassword(userDTO.getPassword()));
        newUser.setFirst_name(userDTO.getFirstName());
        newUser.setLast_name(userDTO.getLastName());
        newUser.setAge(userDTO.getAge());
        userRepository.save(newUser);

        return newUser;
    }

    public String login(UserLoginRequest userLoginRequest) {
        Optional<User> user = userRepository.findByEmail(userLoginRequest.getEmail());
        if (user.isPresent() && checkPassword(userLoginRequest.getPassword(), user.get().getPassword())) {
            return tokenService.generateToken(userLoginRequest);
        }
        throw new LoginException();
    }

    public String encodePassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    public boolean checkPassword(String plainPassword, String encodedPassword) {
        return passwordEncoder.matches(plainPassword, encodedPassword);
    }

}
