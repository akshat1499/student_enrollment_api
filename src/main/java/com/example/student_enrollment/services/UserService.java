package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.User;
import com.example.student_enrollment.exceptions.UserNotFoundException;
import com.example.student_enrollment.pojos.UserPOJO;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers(Integer pageNo, Integer pageSize, String sortBy,String sortDirection, String userRole);

    public User getUserById(Long id) throws UserNotFoundException;

    public User updateContact(String contact, Long id);

    public User saveUser(UserPOJO newUser);

    void  deleteUserById(long id);
}
