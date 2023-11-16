package com.andresinternship.linkedinclone.service;

import com.andresinternship.linkedinclone.exceptions.LoginException;
import com.andresinternship.linkedinclone.exceptions.UserAlreadyExistsException;
import com.andresinternship.linkedinclone.controller.dto.UserLoginRequest;
import com.andresinternship.linkedinclone.model.User;
import com.andresinternship.linkedinclone.repository.UserRepository;
import com.andresinternship.linkedinclone.service.model.RegisterUserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(RegisterUserData user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(encodePassword(user.getPassword()));
        newUser.setFirst_name(user.getFirstName());
        newUser.setLast_name(user.getLastName());
        newUser.setAge(user.getAge());
        userRepository.save(newUser);

        return newUser;
    }

    public String login(String email, String pass) {
        Optional<User> user = userRepository.findByEmail(email);

        validateUser(user);

        return TokenService.generateToken(user.get());
    }

    private void validateUser(Optional<User> user) {
        if (user.isEmpty()) {
            // throw exception with corresponding message
        }

        //
//        if (checkPassword(userLoginRequest.getPassword(), user.get().getPassword())) {
//            // throw exception with corresponding message
//        }
    }

    public String encodePassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    public boolean checkPassword(String plainPassword, String encodedPassword) {
        return passwordEncoder.matches(plainPassword, encodedPassword);
    }

}
