package com.andresinternship.linkedinclone.controller.dto;

import lombok.Data;

@Data
public class UserRegistrationRequestDto {

    private String userEmail;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private Integer userAge;

}
