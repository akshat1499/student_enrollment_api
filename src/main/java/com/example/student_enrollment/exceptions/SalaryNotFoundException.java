package com.example.student_enrollment.exceptions;

public class SalaryNotFoundException extends RuntimeException{
    public SalaryNotFoundException(Long id){
        super("Could Not find Salary with id: "+ id);
    }

}
