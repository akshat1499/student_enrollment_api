package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.entities.Salary;
import com.example.student_enrollment.exceptions.DepartmentNotFoundException;
import com.example.student_enrollment.exceptions.SalaryNotFoundException;
import com.example.student_enrollment.pojos.SalaryPOJO;
import com.example.student_enrollment.utillities.Status;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;
import java.util.List;

@Service("salaryService")
public interface SalaryService {

    public List<Salary> getAllSalaries();

    public Salary getSalaryById(Long id) throws SalaryNotFoundException;

    public Salary updateSalaryStatus(Status newStatus, Long id);

    public Salary saveSalary(SalaryPOJO newSalary);

    void  deleteSalaryById(long id);

    public List<Salary> getTopByAmount(Integer n,Integer order);


}
