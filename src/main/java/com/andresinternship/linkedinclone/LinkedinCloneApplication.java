package com.andresinternship.linkedinclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class LinkedinCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkedinCloneApplication.class, args);
    }

}
