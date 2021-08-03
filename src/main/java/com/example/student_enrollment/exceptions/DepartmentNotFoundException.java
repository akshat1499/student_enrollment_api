package com.example.student_enrollment.exceptions;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(Long id){
        super("Could Not find Department with id: "+ id);
    }

}
