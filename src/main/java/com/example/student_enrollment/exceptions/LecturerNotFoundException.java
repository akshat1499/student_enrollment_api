package com.example.student_enrollment.exceptions;

public class LecturerNotFoundException extends RuntimeException{
    public LecturerNotFoundException(Long id){
        super("Could Not find Lecturer with id: "+ id);

    }
}
