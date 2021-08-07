package com.example.student_enrollment.repositories;

import com.example.student_enrollment.entities.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {

    @Query("FROM Salary")
    List<Salary> findTopOrderBySalary(Pageable pageable);
}
