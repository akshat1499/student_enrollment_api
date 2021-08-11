package com.example.student_enrollment.controller_advice;

import com.example.student_enrollment.exceptions.InvalidValueException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class InvalidValueAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidValueFoundHandler(InvalidValueException ex) {
        return ex.getMessage();
    }
}