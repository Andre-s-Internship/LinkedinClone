package com.andresinternship.linkedinclone.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserLoginRequest {

    private String email;
    private String password;

}
