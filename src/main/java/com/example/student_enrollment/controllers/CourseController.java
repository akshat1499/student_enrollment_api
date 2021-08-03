package com.example.student_enrollment.controllers;


import com.example.student_enrollment.entities.Course;
import com.example.student_enrollment.services.CourseService;
import org.apache.catalina.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Qualifier("courseService")
public class CourseController {

    private CourseService courseService;

    @GetMapping("/courses")
    List<Course> all(){
        return courseService.getAllCourses();
    }

    @PostMapping("/courses")
    Course newCourse(@RequestBody Course newCourse) {
        return courseService.saveCourse(newCourse);
    }


    @GetMapping("/courses/{id}")
    Course one(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PutMapping("/courses/{id}")
    Course replaceCourse(@RequestBody Course newCourse, @PathVariable Long id) {
        return courseService.updateCourse(newCourse,id);
    }
    @PutMapping("/courses/upD/{id}")
    void updateDept(@RequestBody Long did, @PathVariable Long id) {
         courseService.setDepartmentById(id,did);
    }

    @DeleteMapping("/courses/{id}")
    void deleteEmployee(@PathVariable Long id) {
        courseService.deleteCourseById(id);
    }
}

