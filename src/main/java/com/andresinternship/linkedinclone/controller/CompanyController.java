package com.andresinternship.linkedinclone.controller;

import com.andresinternship.linkedinclone.exceptions.InvalidTokenException;
import com.andresinternship.linkedinclone.model.Company;
import com.andresinternship.linkedinclone.controller.dto.CompanyCreationRequest;
import com.andresinternship.linkedinclone.repository.CompanyRepository;
import com.andresinternship.linkedinclone.service.CompanyService;
import com.andresinternship.linkedinclone.service.TokenValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TokenValidation tokenValidation;

    @PostMapping()
    public ResponseEntity createCompany(@RequestBody CompanyCreationRequest companyCreationRequest,
                                        @RequestHeader(name = "Authorization") String token) {
        try {
            tokenValidation.validateToken(token);
        } catch (InvalidTokenException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        // move to service
        if (companyRepository.findByName(companyCreationRequest.getName()).isPresent()) {
            return new ResponseEntity<>("Company already exists", HttpStatus.CONFLICT);
        }

        Company company = companyService.createCompany(companyCreationRequest);
        String body = "Company " + company.getName() + " created successfully";
        // return some company dto un response
        return new ResponseEntity<>(body, HttpStatus.CREATED);

    }

}
