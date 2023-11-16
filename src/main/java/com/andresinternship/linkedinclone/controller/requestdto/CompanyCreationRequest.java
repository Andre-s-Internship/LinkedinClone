package com.andresinternship.linkedinclone.controller.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
public class CompanyCreationRequest {

    private String name;
    private String description;
    private String token;

}
