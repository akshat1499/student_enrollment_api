package com.example.student_enrollment.repositories;

import com.example.student_enrollment.entities.Semester;
import com.example.student_enrollment.utillities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester,Long> {
    List<Semester> findSemesterByStatusEquals(Status status);
}
