package com.example.student_enrollment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentEnrollmentApplication {

    public static void main(String[] args) {
        try{
            SpringApplication.run(StudentEnrollmentApplication.class, args);
        } catch (Throwable e) {
            System.out.println("FATAL ERROR - Build NOT Successful: terminating Spring application");
            System.out.println(e);
        }
    }

}
