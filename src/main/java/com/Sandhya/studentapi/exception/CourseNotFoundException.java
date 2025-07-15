package com.Sandhya.studentapi.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String message) {
        super(message);
    }
}
