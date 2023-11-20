package com.andresinternship.linkedinclone.controller.aspects;

import com.andresinternship.linkedinclone.exceptions.AuthException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RegistrationExceptionAspect {

    @AfterThrowing(pointcut = "execution(* com.andresinternship.linkedinclone.service.UserService.registerUser())", throwing = "ex")
    public ResponseEntity<?> handleRegistrationExceptions(JoinPoint pjp, RuntimeException ex) {
        if (ex instanceof AuthException) {
            System.out.println(pjp.getSourceLocation().getFileName());
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.UNAUTHORIZED);
        }
    }
}
