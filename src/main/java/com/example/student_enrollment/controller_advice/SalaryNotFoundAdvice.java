package com.example.student_enrollment.controller_advice;

import com.example.student_enrollment.exceptions.DepartmentNotFoundException;
import com.example.student_enrollment.exceptions.SalaryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SalaryNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SalaryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String salaryNotFoundHandler(DepartmentNotFoundException ex) {
        return ex.getMessage();
    }
}
