package com.andresinternship.linkedinclone.service;

import com.andresinternship.linkedinclone.exceptions.CompanyException;
import com.andresinternship.linkedinclone.model.Company;
import com.andresinternship.linkedinclone.model.User;
import com.andresinternship.linkedinclone.repository.CompanyRepository;
import com.andresinternship.linkedinclone.repository.UserRepository;
import com.andresinternship.linkedinclone.controller.dto.request.CompanyCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    public Company createCompany(CompanyCreationRequest companyCreationRequest, String token) {

        if (companyRepository.findByName(companyCreationRequest.getName()).isPresent()) {
            throw new CompanyException("Company with specified name already exists");
        }

        Company company = new Company();
        company.setName(companyCreationRequest.getName());
        company.setDescription(companyCreationRequest.getDescription());

        String userEmail = TokenService.extractEmailFromToken(token);
        Optional<User> owner = userRepository.findByEmail(userEmail);
        company.setOwner(owner.get());

        companyRepository.save(company);
        return company;
    }
}
