package com.example.student_enrollment.repositories;

import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.pojos.DepartmentPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}