package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Course;
import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.exceptions.CourseNotFoundException;
import com.example.student_enrollment.exceptions.DepartmentNotFoundException;
import com.example.student_enrollment.exceptions.UserNotFoundException;
import com.example.student_enrollment.pojos.CoursePOJO;
import com.example.student_enrollment.repositories.CourseRepository;
import com.example.student_enrollment.repositories.DepartmentRepository;
import com.example.student_enrollment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("courseService")
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) throws CourseNotFoundException {
        return courseRepository.findById(id).orElseThrow(()-> new CourseNotFoundException(id));
    }

    @Override
    public Course updateCourse(CoursePOJO newCourse, Long id) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setName(newCourse.getName());
                    course.setFee(newCourse.getFee());
                   // course.setInstructor(userRepository.findById(newCourse.getInstId()).orElseThrow(()-> new UserNotFoundException(newCourse.getInstId())));
                    course.setDepartment(departmentRepository.findById(newCourse.getDeptId()).orElseThrow(()-> new DepartmentNotFoundException(newCourse.getDeptId())));
                    return courseRepository.save(course);
                })
                .orElseGet(() -> {
                    Course course = new Course();
                    course.setFee(newCourse.getFee());
                    course.setName(newCourse.getName());
                    course.setDepartment(departmentRepository.findById(newCourse.getDeptId()).orElseThrow(() -> new DepartmentNotFoundException(newCourse.getDeptId())));
                    //course.setInstructor(userRepository.findById(newCourse.getInstId()).orElseThrow(()-> new UserNotFoundException(newCourse
                    //.getInstId())));
                    return courseRepository.save(course);

                });
    }

    @Override
    public Course saveCourse(CoursePOJO newCourse) {
        Course course = new Course();
        course.setFee(newCourse.getFee());
        course.setName(newCourse.getName());
        course.setDepartment(departmentRepository.findById(newCourse.getDeptId()).orElseThrow(() -> new DepartmentNotFoundException(newCourse.getDeptId())));
        //course.setInstructor(userRepository.findById(newCourse.getInstId()).orElseThrow(()-> new UserNotFoundException(newCourse
        //.getInstId())));
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourseById(long id) {
        courseRepository.deleteById(id);
    }


}
