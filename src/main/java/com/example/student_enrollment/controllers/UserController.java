package com.example.student_enrollment.controllers;

import com.example.student_enrollment.entities.User;
import com.example.student_enrollment.pojos.UserPOJO;
import com.example.student_enrollment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @GetMapping("/users")
    List<User> all(@RequestParam(defaultValue = "0") Integer pageNo,
                   @RequestParam(defaultValue = "3") Integer pageSize,
                   @RequestParam(defaultValue = "id") String sortBy,
                   @RequestParam(defaultValue = "ASC") String sortDirection,
                   @RequestParam(defaultValue = "") String userRole){
        return userService.getAllUsers(pageNo,pageSize,sortBy,sortDirection,userRole);
    }

    @PostMapping("/users")
    User newUser(@RequestBody UserPOJO newUser) {
        return userService.saveUser(newUser);
    }


    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/users/contact/{id}")
    User replaceUser(@RequestBody String newContact, @PathVariable Long id) {

        return userService.updateContact(newContact,id);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
