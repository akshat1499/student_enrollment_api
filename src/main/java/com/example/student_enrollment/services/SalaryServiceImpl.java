package com.example.student_enrollment.services;


import com.example.student_enrollment.entities.Salary;
import com.example.student_enrollment.entities.User;
import com.example.student_enrollment.exceptions.LecturerNotFoundException;
import com.example.student_enrollment.exceptions.SalaryNotFoundException;
import com.example.student_enrollment.exceptions.UserNotFoundException;
import com.example.student_enrollment.pojos.SalaryPOJO;
import com.example.student_enrollment.repositories.SalaryRepository;
import com.example.student_enrollment.repositories.UserRepository;
import com.example.student_enrollment.utillities.Status;
import com.example.student_enrollment.utillities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;

@Service("salaryService")
public class SalaryServiceImpl implements SalaryService{

    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }

    @Override
    public Salary getSalaryById(Long id) throws SalaryNotFoundException {
        return salaryRepository.findById(id).orElseThrow(()-> new SalaryNotFoundException(id));
    }

    @Override
    public Salary updateSalaryStatus(Status newStatus, Long id){
        return salaryRepository.findById(id)
                .map(salary -> {
                    salary.setStatus(newStatus);
                    return salaryRepository.save(salary);
                })
                .orElseThrow(() -> new SalaryNotFoundException(id));}

    @Override
    public Salary saveSalary(SalaryPOJO newSalary) {
        Salary salary = new Salary(newSalary.getCreated(), newSalary.getPeriodFrom(),newSalary.getPeriodTo(), newSalary.getAmount(),newSalary.getStatus());
        User newUser = userRepository.findById(newSalary.getUserId()).orElseThrow(()->new UserNotFoundException(newSalary.getUserId()));
        if(newUser.getRole()!= UserRole.LECTURER)
            throw new LecturerNotFoundException(newSalary.getUserId());

        salary.setUser(newUser);
        return salaryRepository.save(salary);
    }

    @Override
    public void deleteSalaryById(long id) {
        salaryRepository.deleteById(id);
    }

    @Override
    public List<Salary> getTop2ByOrderByAmountAsc() {
        return salaryRepository.findTop2ByOrderByAmountAsc();
    }

    @Override
    public List<Salary> getTop2ByOrderByAmountDesc() {
        return salaryRepository.findTop2ByOrderByAmountDesc();
    }
}

