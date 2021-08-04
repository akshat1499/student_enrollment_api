package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Semester;
import com.example.student_enrollment.entities.User;
import com.example.student_enrollment.exceptions.DepartmentNotFoundException;
import com.example.student_enrollment.exceptions.UserNotFoundException;
import com.example.student_enrollment.pojos.UserPOJO;
import com.example.student_enrollment.repositories.DepartmentRepository;
import com.example.student_enrollment.repositories.SemesterRepository;
import com.example.student_enrollment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        User user = new User();
        user.setName(newUser.getName());
        user.setDob(newUser.getDob());
        user.setAddress(newUser.getAddress());
        user.setContact(newUser.getContact());
        user.setRole(newUser.getRole());
        user.setJoinDate(newUser.getJoinDate());
        user.setLeaveDate(newUser.getLeaveDate());
        user.setStatus(newUser.getStatus());
        user.setDepartmentUser(departmentRepository.findById(newUser.getDeptId()).orElseThrow(()-> new DepartmentNotFoundException(newUser.getDeptId())));
        List<Semester> sems = new ArrayList<>();
        sems.add(semesterRepository.getById(newUser.getSemesterId()));
        user.setSemestersEnrolledByUser(sems);
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

}
