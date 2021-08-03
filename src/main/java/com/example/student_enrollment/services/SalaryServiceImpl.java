package com.example.student_enrollment.services;


import com.example.student_enrollment.entities.Salary;
import com.example.student_enrollment.exceptions.SalaryNotFoundException;
import com.example.student_enrollment.repositories.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("salaryService")
public class SalaryServiceImpl implements SalaryService{

    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }

    @Override
    public Salary getSalaryById(Long id) throws SalaryNotFoundException {
        return salaryRepository.findById(id).orElseThrow(()-> new SalaryNotFoundException(id));
    }

    @Override
    public Salary updateSalary(Salary newSalary, Long id){
        return salaryRepository.findById(id)
                .map(salary -> {
                    salary.setAmount(newSalary.getAmount());
                    return salaryRepository.save(salary);
                })
                .orElseGet(() -> {
                    newSalary.setId(id);
                    return salaryRepository.save(newSalary);
                });
    }

    @Override
    public Salary saveSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public void deleteSalaryById(long id) {
        salaryRepository.deleteById(id);
    }
}

