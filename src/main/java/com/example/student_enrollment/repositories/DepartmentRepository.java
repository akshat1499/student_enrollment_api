package com.example.student_enrollment.repositories;

import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.pojos.DepartmentPOJO;
import com.example.student_enrollment.utillities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    List<Department> findDepartmentByStatusEquals(Status status);

}