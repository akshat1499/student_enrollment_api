package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.entities.Semester;
import com.example.student_enrollment.exceptions.SemesterNotFoundException;
import com.example.student_enrollment.pojos.SemesterPOJO;

import java.util.List;


public interface SemesterService {
    public List<Semester> getAllSemesters();

    public Semester getSemesterById(Long id) throws SemesterNotFoundException;

    public Semester updateSemester(Semester newSemester, Long id);

    public Semester saveSemester(SemesterPOJO newSemester);

    public Semester registerCourses(List<Long> courseIdList, Long semesterId);

    public Semester registerUsers(List<Long> userIdList, Long semesterId);

    void  deleteSemesterById(long id);
}
