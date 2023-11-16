package com.andresinternship.linkedinclone.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {

    private String message;

    private String jwtToken;

}
