package com.andresinternship.linkedinclone.utils;

import com.andresinternship.linkedinclone.controller.dto.RegisterUserResponseDto;
import com.andresinternship.linkedinclone.controller.dto.UserRegistrationRequestDto;
import com.andresinternship.linkedinclone.model.User;
import com.andresinternship.linkedinclone.service.model.RegisterUserData;

public class AuthDtoConverter {

    public static RegisterUserData fromDto(UserRegistrationRequestDto dto) {
        RegisterUserData userData = new RegisterUserData();
        userData.setAge(dto.getUserAge());
        // ...

        return userData;
    }

    public static RegisterUserResponseDto toDto(User user) {
        RegisterUserResponseDto dto = new RegisterUserResponseDto();

        return dto;
    }

}
