package com.andresinternship.linkedinclone.controller.requestdto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserRegistrationRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;

}
