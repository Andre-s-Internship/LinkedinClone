package com.andresinternship.linkedinclone.exceptions;

public class RequestValidationException extends RuntimeException{

    public RequestValidationException() {
        super ("Request validation failed");
    }
}
