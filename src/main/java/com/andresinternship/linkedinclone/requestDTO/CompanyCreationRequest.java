package com.andresinternship.linkedinclone.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CompanyCreationRequest {

    private String name;
    private String description;
    private String token;

}
