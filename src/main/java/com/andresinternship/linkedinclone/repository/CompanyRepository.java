package com.andresinternship.linkedinclone.repository;

import com.andresinternship.linkedinclone.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    Optional<Company> findByName(String name);
}
