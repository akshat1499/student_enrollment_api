package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.entities.Salary;
import com.example.student_enrollment.exceptions.DepartmentNotFoundException;
import com.example.student_enrollment.exceptions.SalaryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("salaryService")
public interface SalaryService {

    public List<Salary> getAllSalaries();

    public Salary getSalaryById(Long id) throws SalaryNotFoundException;

    public Salary updateSalary(Salary newSalary, Long id);

    public Salary saveSalary(Salary salary);

    void  deleteSalaryById(long id);
    
}
