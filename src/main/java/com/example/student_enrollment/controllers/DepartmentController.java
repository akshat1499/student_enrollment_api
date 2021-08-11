package com.example.student_enrollment.controllers;

import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.exceptions.InternalServerErrorException;
import com.example.student_enrollment.pojos.DepartmentPOJO;
import com.example.student_enrollment.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
public class DepartmentController {


    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

    @GetMapping("/departments")
    List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }


    @PostMapping("/departments")
    List<Department> newDepartment(@RequestBody List<DepartmentPOJO> newDepartments) {

        List<CompletableFuture<Department>> completableFutures=
                newDepartments.stream().map(newDepartmentPOJO->
                        departmentService.saveDepartment(newDepartmentPOJO))
                        .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]));

        CompletableFuture<List<Department>> allCompletableFuture =
                allFutures.thenApply(future->{
                     return completableFutures.stream()
                            .map(completableFuture-> completableFuture.join())
                            .collect(Collectors.toList());
                });


        try{
            return allCompletableFuture.get();
        }catch (Exception e){
            System.out.println(e);
            throw new InternalServerErrorException("Unable to save departments");

        }

    }


    @GetMapping("/departments/{id}")
    @Cacheable(value = "dept-single", key = "#id")
    public Department one(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/departments/{id}")
    @CachePut(value = "dept-single", key = "#id")
    public Department replaceDepartment(@RequestBody DepartmentPOJO newDepartment, @PathVariable Long id) {
        //Todo: Add validation if recieve empty Json request
        return departmentService.updateDepartment(newDepartment,id);
    }

    @DeleteMapping("/departments/{id}")
    void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
    }
}
