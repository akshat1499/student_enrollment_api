package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.exceptions.DepartmentNotFoundException;
import com.example.student_enrollment.pojos.DepartmentPOJO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DepartmentService {
    public List<Department> getAllDepartments();

    public Department getDepartmentById(Long id) throws DepartmentNotFoundException;

    public Department updateDepartment(DepartmentPOJO newDepartment, Long id );

    public CompletableFuture<Department> saveDepartment(DepartmentPOJO newDepartment);

    void  deleteDepartmentById(long id);
}
