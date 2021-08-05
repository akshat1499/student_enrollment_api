package com.example.student_enrollment.controllers;

import com.example.student_enrollment.entities.Semester;
import com.example.student_enrollment.pojos.SemesterPOJO;
import com.example.student_enrollment.services.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SemesterController {
    @Autowired
    @Qualifier("semesterService")
    private SemesterService semesterService;

    @GetMapping("/semesters")
    List<Semester> all(){
        return semesterService.getAllSemesters();
    }

    @PostMapping("/semesters")
    Semester newSemester(@RequestBody SemesterPOJO newSemester) {
        return semesterService.saveSemester(newSemester);
    }


    @GetMapping("/semesters/{id}")
    Semester one(@PathVariable Long id) {
        return semesterService.getSemesterById(id);
    }

    @PutMapping("/semesters/courses/{id}")
    Semester registerCourseInSemester(@RequestBody List<Long> courseList, @PathVariable Long id) {
        return semesterService.registerCourses(courseList,id);
    }

    @PutMapping("/semesters/users/{id}")
    Semester registerUsersInSemester(@RequestBody List<Long> userList, @PathVariable Long id) {

        return semesterService.registerUsers(userList,id);
    }

    @DeleteMapping("/semesters/{id}")
    void deleteSemester(@PathVariable Long id) {
        semesterService.deleteSemesterById(id);
    }

}
