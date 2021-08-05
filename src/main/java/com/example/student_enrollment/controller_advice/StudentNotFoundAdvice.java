package com.example.student_enrollment.controller_advice;

import com.example.student_enrollment.exceptions.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ControllerAdvice
    public class StudentNotFoundAdvice {
        @ResponseBody
        @ExceptionHandler(StudentNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String studenttNotFoundHandler(StudentNotFoundException ex) {
            return ex.getMessage();
        }

    }

