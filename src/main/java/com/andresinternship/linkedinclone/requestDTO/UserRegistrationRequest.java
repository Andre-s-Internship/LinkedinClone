package com.andresinternship.linkedinclone.requestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;

}
