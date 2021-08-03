package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Course;
import com.example.student_enrollment.exceptions.CourseNotFoundException;


import java.util.List;

public interface CourseService {
    public List<Course> getAllCourses();

    public Course getCourseById(Long id) throws CourseNotFoundException;

    public Course updateCourse(Course newCourse, Long id);

    public Course saveCourse(Course course);

    public void  deleteCourseById(long id);

    public void setDepartmentById(long courseId, long deptId) throws CourseNotFoundException;
}
