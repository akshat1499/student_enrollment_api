package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.exceptions.DepartmentNotFoundException;
import com.example.student_enrollment.pojos.DepartmentPOJO;
import com.example.student_enrollment.repositories.DepartmentRepository;
import com.example.student_enrollment.utillities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public Department updateDepartment(DepartmentPOJO newDepartment, Long id){
        return departmentRepository.findById(id)
                .map(department -> {
                    if(newDepartment.getName()!=null) department.setName(newDepartment.getName());
                    if(newDepartment.getStatus()!=null) department.setStatus(newDepartment.getStatus());

                    return departmentRepository.save(department);
                })
                .orElseGet(() -> {
                    //newDepartment.setId(id);
                    Department department = new Department();
                    return departmentRepository.save(department);
                });
    }

    @Override
    public Department saveDepartment(DepartmentPOJO newDepartment) {
       DepartmentPOJO.validate(newDepartment);

        Department department= new Department(newDepartment.getName(), Status.ACTIVE);
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartmentById(long id) {
        departmentRepository.deleteById(id);
    }
}
