package com.andresinternship.linkedinclone.controller.aspects;

import com.andresinternship.linkedinclone.exceptions.AppException;
import com.andresinternship.linkedinclone.controller.dto.response.BasicResponse;
import com.andresinternship.linkedinclone.exceptions.AuthException;
import com.andresinternship.linkedinclone.exceptions.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    private ResponseEntity<Object> handleException(AppException exception) {
        if (exception instanceof AuthException) {
            return new ResponseEntity<>(new BasicResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
        } else if (exception instanceof InvalidTokenException) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(new BasicResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
