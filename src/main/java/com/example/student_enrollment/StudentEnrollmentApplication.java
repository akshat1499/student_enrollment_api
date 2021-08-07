package com.example.student_enrollment;

import com.example.student_enrollment.entities.Department;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

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
