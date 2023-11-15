package com.andresinternship.linkedinclone.exceptions;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super("User is not authenticated");
    }

}
