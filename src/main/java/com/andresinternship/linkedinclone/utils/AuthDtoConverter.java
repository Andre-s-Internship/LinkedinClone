package com.andresinternship.linkedinclone.utils;

import com.andresinternship.linkedinclone.controller.dto.response.UserRegistrationResponseDto;
import com.andresinternship.linkedinclone.controller.dto.request.UserRegistrationRequestDto;
import com.andresinternship.linkedinclone.model.User;
import com.andresinternship.linkedinclone.service.model.RegisterUserData;

public class AuthDtoConverter {

    public static RegisterUserData fromDto(UserRegistrationRequestDto dto) {
        RegisterUserData userData = new RegisterUserData();
        userData.setAge(dto.getUserAge());
        userData.setEmail(dto.getUserEmail());
        userData.setPassword(dto.getUserPassword());
        userData.setFirstName(dto.getUserFirstName());
        userData.setLastName(dto.getUserLastName());

        return userData;
    }

    public static UserRegistrationResponseDto toDto(User user) {
        UserRegistrationResponseDto dto = new UserRegistrationResponseDto();
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirst_name());
        dto.setLastName(user.getLast_name());
        return dto;
    }

}
