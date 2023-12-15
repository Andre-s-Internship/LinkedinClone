package com.andresinternship.linkedinclone.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginResponseDto {

    private String message;
    private String jwtToken;

}
