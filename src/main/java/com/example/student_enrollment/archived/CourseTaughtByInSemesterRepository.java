package com.example.student_enrollment.archived;

import com.example.student_enrollment.archived.CourseTaughtByInSemester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTaughtByInSemesterRepository extends JpaRepository<CourseTaughtByInSemester,Long> {
}
