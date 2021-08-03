package com.example.student_enrollment.exceptions;

public class CourseNotFoundException extends RuntimeException {

        public CourseNotFoundException(Long id){
            super("Could Not find Course with id: "+ id);
        }

}
