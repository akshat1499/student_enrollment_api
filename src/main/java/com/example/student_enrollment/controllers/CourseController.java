package com.example.student_enrollment.controllers;


import com.example.student_enrollment.entities.Course;
import com.example.student_enrollment.pojos.CoursePOJO;
import com.example.student_enrollment.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Qualifier("courseService")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    List<Course> all(){
        return courseService.getAllCourses();
    }

    @PostMapping("/courses")
    Course newCourse(@RequestBody CoursePOJO newCourse) {
        return courseService.saveCourse(newCourse);
    }


    @GetMapping("/courses/{id}")
    Course one(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PutMapping("/courses/{id}")
    Course replaceCourse(@RequestBody CoursePOJO newCourse, @PathVariable Long id) {
        return courseService.updateCourse(newCourse,id);
    }

    @DeleteMapping("/courses/{id}")
    void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
    }
}

