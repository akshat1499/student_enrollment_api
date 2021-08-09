package com.example.student_enrollment.exceptions;

public class InvalidValueException extends RuntimeException{

    public InvalidValueException(String fieldname){

        super("Please enter a valid value for "+ fieldname);
    }
}
