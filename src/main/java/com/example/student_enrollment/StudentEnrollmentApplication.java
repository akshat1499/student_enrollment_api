package com.example.student_enrollment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@Configuration
@EnableCaching
public class StudentEnrollmentApplication {

//    @Bean
//    JedisConnectionFactory jedisConnectionFactory(){
//        return new JedisConnectionFactory();
//    }

//    @Bean
//    RedisTemplate<String, Object> redisTemplate(){
//        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String, Object>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//        return redisTemplate;
//    }
    public static void main(String[] args) {
        try{
            SpringApplication.run(StudentEnrollmentApplication.class, args);
        } catch (Throwable e) {
            System.out.println("FATAL ERROR - Build NOT Successful: terminating Spring application");
            System.out.println(e);
        }
    }

}
