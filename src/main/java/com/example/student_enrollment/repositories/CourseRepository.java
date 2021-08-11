package com.example.student_enrollment.repositories;

import com.example.student_enrollment.entities.Course;
import com.example.student_enrollment.utillities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    List<Course> findCourseByStatusEquals(Status status);
}
