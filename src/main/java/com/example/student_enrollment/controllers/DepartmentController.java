package com.example.student_enrollment.controllers;

import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

    @GetMapping("/departments")
    List<Department> all(){
        return departmentService.getAllDepartments();
    }

    @PostMapping("/departments")
    Department newDepartment(@RequestBody Department newDepartment) {
        return departmentService.saveDepartment(newDepartment);
    }


    @GetMapping("/departments/{id}")
    @Cacheable(value = "dept-single", key = "#id")
    public Department one(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/departments/{id}")
    @CachePut(value = "dept-single", key = "#id")
    public Department replaceDepartment(@RequestBody Department newDepartment, @PathVariable Long id) {

        return departmentService.updateDepartment(newDepartment,id);
    }

    @DeleteMapping("/departments/{id}")
    void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
    }
}
