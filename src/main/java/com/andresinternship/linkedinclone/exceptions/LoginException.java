package com.andresinternship.linkedinclone.exceptions;

public class LoginException extends RuntimeException {

    public LoginException() {
        super("Credentials are not valid");
    }
}