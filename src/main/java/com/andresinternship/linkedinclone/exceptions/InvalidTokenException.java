package com.andresinternship.linkedinclone.exceptions;

public class InvalidTokenException extends AppException {
    public InvalidTokenException() {
        super("Authentication token is invalid");
    }
}
