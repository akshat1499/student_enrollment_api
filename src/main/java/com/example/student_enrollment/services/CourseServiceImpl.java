package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Course;
import com.example.student_enrollment.exceptions.CourseNotFoundException;
import com.example.student_enrollment.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("courseService")
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) throws CourseNotFoundException {
        return courseRepository.findById(id).orElseThrow(()-> new CourseNotFoundException(id));
    }

    @Override
    public Course updateCourse(Course newCourse, Long id) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setName(newCourse.getName());
                    course.setFee(newCourse.getFee());
                    //course.setDepartment(newCourse.getDepartment());
                    return courseRepository.save(course);
                })
                .orElseGet(() -> {
                    newCourse.setId(id);
                    return courseRepository.save(newCourse);
                });
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourseById(long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void setDepartmentById(long courseId, long deptId) {
         courseRepository.findById(courseId).map(course -> {
             course.setDepartmentById(deptId);
                     return null;
                 }
         ).orElseThrow(()-> new CourseNotFoundException(courseId));
    }
}
