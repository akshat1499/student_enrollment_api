package com.example.student_enrollment.exceptions;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(Long id){
        super("Could Not find Student with id: "+ id);

    }
}
