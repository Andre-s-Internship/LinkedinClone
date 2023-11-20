package com.andresinternship.linkedinclone.controller.dto.response;

import lombok.Data;

@Data
public class UserRegistrationResponseDto {

    private String firstName;
    private String lastName;
    private String email;

    @Override
    public String toString() {
        return "User %s %s is registered with email: %s".formatted(firstName, lastName, email);
    }

}
