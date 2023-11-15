package com.andresinternship.linkedinclone.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Authentication token is invalid");
    }
}
