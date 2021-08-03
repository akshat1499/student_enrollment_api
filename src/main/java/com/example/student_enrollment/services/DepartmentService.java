package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.exceptions.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    public List<Department> getAllDepartments();

    public Department getDepartmentById(Long id) throws DepartmentNotFoundException;

    public Department updateDepartment(Department newDepartment, Long id);

    public Department saveDepartment(Department department);

    void  deleteDepartmentById(long id);
}
