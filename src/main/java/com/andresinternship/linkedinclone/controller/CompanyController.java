package com.andresinternship.linkedinclone.controller;

import com.andresinternship.linkedinclone.controller.dto.response.CompanyCreationResponseDto;
import com.andresinternship.linkedinclone.exceptions.InvalidTokenException;
import com.andresinternship.linkedinclone.model.Company;
import com.andresinternship.linkedinclone.controller.dto.request.CompanyCreationRequest;
import com.andresinternship.linkedinclone.service.CompanyService;
import com.andresinternship.linkedinclone.service.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping()
    public ResponseEntity<?> createCompany(@RequestBody CompanyCreationRequest companyCreationRequest,
                                           @RequestHeader(name = "Authorization") String token) {
        try {
            TokenValidator.validateToken(token);
        } catch (InvalidTokenException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        Company company = companyService.createCompany(companyCreationRequest, token);
        CompanyCreationResponseDto companyResponseDto = new CompanyCreationResponseDto();
        companyResponseDto.setName(company.getName());
        companyResponseDto.setDescription(company.getDescription());

        return new ResponseEntity<>(companyResponseDto, HttpStatus.CREATED);
    }

}
