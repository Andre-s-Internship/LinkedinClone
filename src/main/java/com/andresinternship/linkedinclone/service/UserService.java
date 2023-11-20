package com.andresinternship.linkedinclone.service;

import com.andresinternship.linkedinclone.exceptions.AuthException;
import com.andresinternship.linkedinclone.model.User;
import com.andresinternship.linkedinclone.repository.UserRepository;
import com.andresinternship.linkedinclone.service.helper.TokenGenerator;
import com.andresinternship.linkedinclone.service.model.RegisterUserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(RegisterUserData user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new AuthException("User already exists");
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

    public String login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        validateUser(user, password);
        return TokenService.generateToken(user.get());
    }

    private void validateUser(Optional<User> user, String plainPassword) {
        if (user.isEmpty()) {
            throw new AuthException("User with given credentials is not found");
        }
        if (checkPassword(plainPassword, user.get().getPassword())) {
            throw new AuthException("Credentials are not valid");
        }
    }

    private String encodePassword(String plainPassword) {
        return TokenGenerator.hashString(plainPassword);
    }

    private boolean checkPassword(String plainPassword, String encodedPassword) {
        String encodedPlainPassword = TokenGenerator.hashString(plainPassword);
        return encodedPlainPassword.equals(encodedPassword);
    }

}
