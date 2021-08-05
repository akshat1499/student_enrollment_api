package com.example.student_enrollment.controllers;

import com.example.student_enrollment.entities.Salary;
import com.example.student_enrollment.pojos.SalaryPOJO;
import com.example.student_enrollment.services.SalaryService;
import com.example.student_enrollment.utillities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalaryController {
    @Autowired
    @Qualifier("salaryService")
    private SalaryService salaryService;

    @GetMapping("/salaries")
    List<Salary> all(){
        return salaryService.getAllSalaries();
    }

    @PostMapping("/salaries")
    Salary newSalary(@RequestBody SalaryPOJO newSalary) {
        return salaryService.saveSalary(newSalary);
    }



    @GetMapping("/salaries/{id}")
    Salary one(@PathVariable Long id) {
        return salaryService.getSalaryById(id);
    }


    @GetMapping("/salaries/top2/asc")
    List<Salary> top2asc() {
        return salaryService.getTop2ByOrderByAmountAsc();
    }

    @GetMapping("/salaries/top2/desc")
    List<Salary> top2desc() {
        return salaryService.getTop2ByOrderByAmountDesc();
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
