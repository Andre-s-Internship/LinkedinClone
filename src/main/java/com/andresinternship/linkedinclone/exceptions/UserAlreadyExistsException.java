package com.andresinternship.linkedinclone.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {
        super ("User with these credentials already exists");
    }
}
