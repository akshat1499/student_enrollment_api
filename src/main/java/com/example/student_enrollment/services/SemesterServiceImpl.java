package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Semester;
import com.example.student_enrollment.exceptions.SemesterNotFoundException;
import com.example.student_enrollment.repositories.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("semesterService")
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    @Override
    public Semester getSemesterById(Long id) throws SemesterNotFoundException {
        return semesterRepository.findById(id).orElseThrow(()-> new SemesterNotFoundException(id));
    }

    @Override
    public Semester updateSemester(Semester newSemester, Long id){
        return semesterRepository.findById(id)
                .map(semester -> {
                    semester.setName(newSemester.getName());
                    semester.setStartDate(newSemester.getStartDate());
                    semester.setEndDate(newSemester.getEndDate());
                    return semesterRepository.save(semester);
                })
                .orElseGet(() -> {
                    newSemester.setId(id);
                    return semesterRepository.save(newSemester);
                });
    }

    @Override
    public Semester saveSemester(Semester semester) {
        return semesterRepository.save(semester);
    }

    @Override
    public void deleteSemesterById(long id) {
        semesterRepository.deleteById(id);
    }

}
