package com.andresinternship.linkedinclone.exceptions;

public class CompanyAlreadyExistsException extends RuntimeException {

    public CompanyAlreadyExistsException() {
        super("Company with this name already exists");
    }
}
