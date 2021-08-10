package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Course;
import com.example.student_enrollment.exceptions.CourseNotFoundException;
import com.example.student_enrollment.pojos.CoursePOJO;


import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CourseService {
    public CompletableFuture<List<Course>> getAllCoursesAsync();
    public List<Course> getAllCourses();

    public Course getCourseById(Long id) throws CourseNotFoundException;

    public Course updateCourse(CoursePOJO newCourse, Long id);

    public Course saveCourse(CoursePOJO newCourse);

    public void  deleteCourseById(long id);

}
