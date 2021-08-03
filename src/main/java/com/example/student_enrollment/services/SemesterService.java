package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.entities.Semester;
import com.example.student_enrollment.exceptions.SemesterNotFoundException;

import java.util.List;


public interface SemesterService {
    public List<Semester> getAllSemesters();

    public Semester getSemesterById(Long id) throws SemesterNotFoundException;

    public Semester updateSemester(Semester newSemester, Long id);

    public Semester saveSemester(Semester semester);

    void  deleteSemesterById(long id);
}
