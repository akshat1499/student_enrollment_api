package com.example.student_enrollment.controllers;

import com.example.student_enrollment.entities.Salary;
import com.example.student_enrollment.pojos.SalaryPOJO;
import com.example.student_enrollment.services.SalaryService;
import com.example.student_enrollment.utillities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SalaryController {
    @Autowired
    @Qualifier("salaryService")
    private SalaryService salaryService;

    @GetMapping("/salaries")
    List<Salary> all(@RequestParam(defaultValue = "0") Integer pageNo,
                     @RequestParam(defaultValue = "3") Integer pageSize,
                     @RequestParam(defaultValue = "amount") String sortBy,
                     @RequestParam(defaultValue = "ASC") String sortDirection){
        return salaryService.getAllSalaries(pageNo,pageSize,sortBy,sortDirection);
    }

    @PostMapping("/salaries")
    Salary newSalary(@RequestBody SalaryPOJO newSalary) {
        return salaryService.saveSalary(newSalary);
    }



    @GetMapping("/salaries/instructor/{id}")
    List<Salary> salariesByInstructorId(@PathVariable Long id) {
        return salaryService.getAllSalariesByInstructorId(id);
    }
    @GetMapping("/salaries/{id}")
    Salary one(@PathVariable Long id) {
        return salaryService.getSalaryById(id);
    }


    @PutMapping("/salaries/status/{id}")
    Salary replaceSalary(@RequestBody Integer newStatus, @PathVariable Long id) {

        return salaryService.updateSalaryStatus(Status.values()[newStatus],id);
    }

    @DeleteMapping("/salaries/{id}")
    void deleteSalary(@PathVariable Long id) {
        salaryService.deleteSalaryById(id);
    }
}
