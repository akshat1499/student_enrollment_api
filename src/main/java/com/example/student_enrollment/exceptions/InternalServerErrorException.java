package com.example.student_enrollment.exceptions;

public class InternalServerErrorException extends RuntimeException{
    public InternalServerErrorException(String s){
        super(s);
    }
}
