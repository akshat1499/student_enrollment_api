package com.example.student_enrollment.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("Could Not find User with id: "+ id);
    }

}