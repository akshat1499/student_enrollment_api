package com.example.student_enrollment.controller_advice;

import com.example.student_enrollment.exceptions.DepartmentNotFoundException;
import com.example.student_enrollment.exceptions.SemesterNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SemesterNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SemesterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String semesterNotFoundHandler(SemesterNotFoundException ex) {
        return ex.getMessage();
    }
}
