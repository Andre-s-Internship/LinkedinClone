package com.andresinternship.linkedinclone.service;

import com.andresinternship.linkedinclone.model.Company;
import com.andresinternship.linkedinclone.model.TokenGenerator;
import com.andresinternship.linkedinclone.model.User;
import com.andresinternship.linkedinclone.repository.CompanyRepository;
import com.andresinternship.linkedinclone.repository.UserRepository;
import com.andresinternship.linkedinclone.requestDTO.CompanyCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public Company createCompany(CompanyCreationRequest companyCreationRequest) {
        Company company = new Company();
        company.setCompany_id(UUID.randomUUID());
        company.setName(companyCreationRequest.getName());
        company.setDescription(companyCreationRequest.getDescription());

        String userEmail = tokenService.extractEmailFromToken(companyCreationRequest.getToken());
        Optional<User> owner = userRepository.findByEmail(userEmail);
        owner.ifPresent(user -> company.setOwner_id(user.getUser_id()));

        companyRepository.save(company);

        return company;

    }

}
