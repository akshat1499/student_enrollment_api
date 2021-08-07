package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.exceptions.DepartmentNotFoundException;
import com.example.student_enrollment.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

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
    public Department updateDepartment(Department newDepartment, Long id){
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setName(newDepartment.getName());
                    return departmentRepository.save(department);
                })
                .orElseGet(() -> {
                    newDepartment.setId(id);
                    return departmentRepository.save(newDepartment);
                });
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartmentById(long id) {
        departmentRepository.deleteById(id);
    }
}
