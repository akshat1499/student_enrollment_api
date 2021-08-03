package com.example.student_enrollment.controllers;

import com.example.student_enrollment.entities.Semester;
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
    Semester newSemester(@RequestBody Semester newSemester) {
        return semesterService.saveSemester(newSemester);
    }


    @GetMapping("/semesters/{id}")
    Semester one(@PathVariable Long id) {
        return semesterService.getSemesterById(id);
    }

    @PutMapping("/semesters/{id}")
    Semester replaceSemester(@RequestBody Semester newSemester, @PathVariable Long id) {

        return semesterService.updateSemester(newSemester,id);
    }

    @DeleteMapping("/semesters/{id}")
    void deleteEmployee(@PathVariable Long id) {
        semesterService.deleteSemesterById(id);
    }

}
