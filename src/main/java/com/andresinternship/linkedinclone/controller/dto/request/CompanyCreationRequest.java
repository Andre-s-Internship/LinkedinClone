package com.andresinternship.linkedinclone.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CompanyCreationRequest {

    private String name;
    private String description;

}
