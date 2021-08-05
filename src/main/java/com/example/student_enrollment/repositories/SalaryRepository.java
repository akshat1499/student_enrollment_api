package com.example.student_enrollment.repositories;

import com.example.student_enrollment.entities.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {

    public List<Salary> findTop2ByOrderByAmountAsc();

    public List<Salary> findTop2ByOrderByAmountDesc();
}
