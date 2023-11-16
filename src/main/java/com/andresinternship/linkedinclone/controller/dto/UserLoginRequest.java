package com.andresinternship.linkedinclone.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserLoginRequest {

    private String email;
    private String password;

}