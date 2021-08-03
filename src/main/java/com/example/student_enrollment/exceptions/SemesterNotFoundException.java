package com.example.student_enrollment.exceptions;

import com.example.student_enrollment.entities.Semester;

public class SemesterNotFoundException extends RuntimeException{
    public SemesterNotFoundException(Long id){
        super("Could Not find Semester with id: "+ id);
    }

}
