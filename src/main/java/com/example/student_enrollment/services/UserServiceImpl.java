package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Semester;
import com.example.student_enrollment.entities.User;
import com.example.student_enrollment.exceptions.DepartmentNotFoundException;
import com.example.student_enrollment.exceptions.UserNotFoundException;
import com.example.student_enrollment.pojos.UserPOJO;
import com.example.student_enrollment.repositories.DepartmentRepository;
import com.example.student_enrollment.repositories.SemesterRepository;
import com.example.student_enrollment.repositories.UserRepository;
import com.example.student_enrollment.utillities.Status;
import com.example.student_enrollment.utillities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SemesterRepository semesterRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

    @Override
    public User updateContact(String newContact, Long id){
        return userRepository.findById(id).map(user-> {
                    user.setContact(newContact);
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    //Role stud =0 lecturer = 1
    @Override
    public User saveUser(UserPOJO newUser) {


        UserPOJO.validate(newUser);

        User user = new User();
        Date jDate = new Date();
        Date dob = new Date();

        try{
            jDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(newUser.getJoinDate());
            dob = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(newUser.getDob());
        }catch (Exception e){}
        user.setName(newUser.getName());
        user.setDob(dob);
        user.setAddress(newUser.getAddress());
        user.setContact(newUser.getContact());
        user.setRole(UserRole.valueOf(newUser.getRole().toUpperCase()));
        user.setJoinDate(jDate);
        user.setStatus(Status.ACTIVE);
        user.setDepartmentUser(departmentRepository.findById(newUser.getDeptId()).orElseThrow(()-> new DepartmentNotFoundException(newUser.getDeptId())));
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

}
