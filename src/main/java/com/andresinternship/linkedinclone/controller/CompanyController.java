package com.andresinternship.linkedinclone.controller;

import com.andresinternship.linkedinclone.exceptions.AuthenticationException;
import com.andresinternship.linkedinclone.exceptions.CompanyAlreadyExistsException;
import com.andresinternship.linkedinclone.exceptions.UserAlreadyExistsException;
import com.andresinternship.linkedinclone.model.Company;
import com.andresinternship.linkedinclone.requestDTO.CompanyCreationRequest;
import com.andresinternship.linkedinclone.repository.CompanyRepository;
import com.andresinternship.linkedinclone.service.CompanyService;
import com.andresinternship.linkedinclone.service.TokenService;
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
    private TokenService tokenService;


    @PostMapping("/create")
    public ResponseEntity createCompany(@RequestBody CompanyCreationRequest companyCreationRequest) {
        if (!tokenService.validateToken(companyCreationRequest.getToken())) {
            return new ResponseEntity<>("Unauthorized user", HttpStatus.UNAUTHORIZED);
        }

        if (companyRepository.findByName(companyCreationRequest.getName()).isPresent()) {
            return new ResponseEntity<>("Company already exists", HttpStatus.CONFLICT);
        }

        Company company = companyService.createCompany(companyCreationRequest);
        String body = "Company " + company.getName() + " created successfully";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + companyCreationRequest.getToken());
        return new ResponseEntity<>(body, headers, HttpStatus.CREATED);

    }


    @GetMapping("/decoder")
    public String decodeToken() {
//        if (companyRepository.findByName(companyCreationRequest.getName()).isPresent()) {
//            throw new UserAlreadyExistsException();
//        }
        return tokenService.decodeToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIU0EyNTYifQ==.eyJleHBpcmVzX2F0IjoiVHVlIE5vdiAxNCAyMjoxMTo0OSBBTVQgMjAyMyIsImlzc3VlZF9hdCI6IlR1ZSBOb3YgMTQgMjE6NDE6NDkgQU1UIDIwMjMiLCJlbWFpbCI6ImpvaG5kb2VAZXhhbXBsZS5jb20ifQ==.99dd4ff4f35d8b429047ad112cffafaf5385c53fc8ae1eb77f1079570bb12996");

//        companyService.createCompany(companyCreationRequest);
//        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
