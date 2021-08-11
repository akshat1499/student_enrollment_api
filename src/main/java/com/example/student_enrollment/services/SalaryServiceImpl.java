package com.example.student_enrollment.services;


import com.example.student_enrollment.entities.Salary;
import com.example.student_enrollment.entities.User;
import com.example.student_enrollment.exceptions.LecturerNotFoundException;
import com.example.student_enrollment.exceptions.SalaryNotFoundException;
import com.example.student_enrollment.exceptions.UserNotFoundException;
import com.example.student_enrollment.pojos.SalaryPOJO;
import com.example.student_enrollment.repositories.SalaryRepository;
import com.example.student_enrollment.repositories.UserRepository;
import com.example.student_enrollment.utillities.SortDirection;
import com.example.student_enrollment.utillities.Status;
import com.example.student_enrollment.utillities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("salaryService")
public class SalaryServiceImpl implements SalaryService{

    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Salary> getAllSalaries(Integer pageNo, Integer pageSize, String sortBy,String sortDirection) {
//        Pageable paging =  PageRequest.of(pageNo,pageSize,Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()),sortBy));
//        Page<Salary> pagedResult = salaryRepository.findAll(paging);
//        if(pagedResult.hasContent()) {
//            return pagedResult.getContent();
//        } else {
//            return new ArrayList<Salary>();
//        }

        return salaryRepository.findSalaryByStatusEquals(Status.ACTIVE);
    }

    @Override
    public Salary getSalaryById(Long id) throws SalaryNotFoundException {
        return salaryRepository.findById(id).orElseThrow(()-> new SalaryNotFoundException(id));
    }

    @Override
    public List<Salary> getAllSalariesByInstructorId(Long id) throws SalaryNotFoundException {


        return salaryRepository.findSalariesByUserIdEquals(id);
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
        SalaryPOJO.validate(newSalary);
        Date periodFrom = null;
        Date periodTo = null;

        try{
            periodFrom = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(newSalary.getPeriodFrom());
            periodTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(newSalary.getPeriodTo());
        }catch (Exception e){}

        Salary salary = new Salary(periodFrom,periodTo, newSalary.getAmount());
        User newUser = userRepository.findById(newSalary.getUserId()).orElseThrow(()->new UserNotFoundException(newSalary.getUserId()));
        if(newUser.getRole()!= UserRole.LECTURER)
            throw new LecturerNotFoundException(newSalary.getUserId());

        salary.setUser(newUser);
        //salary.setStatus(Status.ACTIVE);
        return salaryRepository.save(salary);
    }

    @Override
    public void deleteSalaryById(long id) {
        Salary salary = salaryRepository.findById(id).orElseThrow(()-> new SalaryNotFoundException(id));
        salary.setStatus(Status.INACTIVE);
        salaryRepository.save(salary);
    }




}

