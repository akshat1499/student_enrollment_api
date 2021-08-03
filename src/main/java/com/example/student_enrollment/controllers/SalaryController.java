package com.example.student_enrollment.controllers;

import com.example.student_enrollment.entities.Salary;
import com.example.student_enrollment.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SalaryController {
    @Autowired
    @Qualifier("salaryService")
    private SalaryService salaryService;

    @GetMapping("/salaries")
    List<Salary> all(){
        return salaryService.getAllSalaries();
    }

    @PostMapping("/salaries")
    Salary newSalary(@RequestBody Salary newSalary) {
        return salaryService.saveSalary(newSalary);
    }


    @GetMapping("/salaries/{id}")
    Salary one(@PathVariable Long id) {
        return salaryService.getSalaryById(id);
    }

    @PutMapping("/salaries/{id}")
    Salary replaceSalary(@RequestBody Salary newSalary, @PathVariable Long id) {

        return salaryService.updateSalary(newSalary,id);
    }

    @DeleteMapping("/salaries/{id}")
    void deleteEmployee(@PathVariable Long id) {
        salaryService.deleteSalaryById(id);
    }
}
