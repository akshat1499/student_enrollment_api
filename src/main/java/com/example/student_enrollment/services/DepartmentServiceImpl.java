package com.example.student_enrollment.services;

import com.example.student_enrollment.configuration.AsyncConfiguration;
import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.exceptions.DepartmentNotFoundException;
import com.example.student_enrollment.pojos.DepartmentPOJO;
import com.example.student_enrollment.repositories.DepartmentRepository;
import com.example.student_enrollment.utillities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private AsyncConfiguration asyncConfiguration;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    @Cacheable(key = "#id")
    public Department getDepartmentById(Long id) throws DepartmentNotFoundException {
        return departmentRepository.findById(id).orElseThrow(()-> new DepartmentNotFoundException(id));
    }

    @Override
    public Department updateDepartment(DepartmentPOJO newDepartment, Long id){
        return departmentRepository.findById(id)
                .map(department -> {
                    if(newDepartment.getName()!=null) department.setName(newDepartment.getName());
                    if(newDepartment.getStatus()!=null) department.setStatus(newDepartment.getStatus());

                    return departmentRepository.save(department);
                })
                .orElseGet(() -> {
                    Department department = new Department();
                    return departmentRepository.save(department);
                });
    }

    @Async
    @Override
    public CompletableFuture<Department> saveDepartment(DepartmentPOJO newDepartment) {

        return CompletableFuture.supplyAsync(()->{
            DepartmentPOJO.validate(newDepartment);
            Department department= new Department(newDepartment.getName(), Status.ACTIVE);
            System.out.println("\n\n\nCURRENTTHREAD:" + Thread.currentThread()+"\n\n\n");
            return departmentRepository.save(department);
        });
    }

    @Override
    public void deleteDepartmentById(long id) {
        departmentRepository.deleteById(id);
    }
}
