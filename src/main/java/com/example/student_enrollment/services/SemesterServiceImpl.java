package com.example.student_enrollment.services;

import com.example.student_enrollment.entities.Course;
import com.example.student_enrollment.entities.Semester;
import com.example.student_enrollment.entities.User;
import com.example.student_enrollment.exceptions.SemesterNotFoundException;
import com.example.student_enrollment.exceptions.StudentNotFoundException;
import com.example.student_enrollment.exceptions.UserNotFoundException;
import com.example.student_enrollment.pojos.SemesterPOJO;
import com.example.student_enrollment.repositories.CourseRepository;
import com.example.student_enrollment.repositories.SemesterRepository;
import com.example.student_enrollment.repositories.UserRepository;
import com.example.student_enrollment.utillities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("semesterService")
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

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
    public Semester saveSemester(SemesterPOJO newSemester) {
        SemesterPOJO.validate(newSemester);
        Date startDate = new Date();
        Date endDate= new Date();

        try{
            startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(newSemester.getStartDate());
            endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(newSemester.getEndDate());
        }catch (Exception e){}
        Semester semester = new Semester(newSemester.getName(), startDate,endDate);

        return semesterRepository.save(semester);
    }

    @Override
    public Semester registerCourses(List<Long> courseIdList, Long semesterId) {

        return semesterRepository.findById(semesterId).map(semester -> {
            List<Course> newCoursesToAdd = new ArrayList<>();
            courseIdList.forEach(courseId ->{
                newCoursesToAdd.add(courseRepository.getById(courseId));
            });
            //newCoursesToAdd.addAll(semester.getCoursesOffered());
            //semester.setCoursesOffered(newCoursesToAdd);
            semester.getCoursesOffered().addAll(newCoursesToAdd);
            return semesterRepository.save(semester);
        }).orElseThrow(()-> new SemesterNotFoundException(semesterId));

    }

    @Override
    public Semester registerUsers(List<Long> userIdList, Long semesterId) {
//        Semester newSem;
//         semesterRepository.findById(semesterId).map(semester -> {
//            System.out.println(semester);
//            List<User> newUsersToAdd = new ArrayList<>();
//            userIdList.forEach(userId ->{
//                User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
//                if(user.getRole()!= UserRole.STUDENT)
//                    throw new StudentNotFoundException(userId);
//
//                newUsersToAdd.add(user);
//            });
//
//            //newUsersToAdd.addAll(semester.getUsersRegisteredInSemester());
//            //semester.setUsersRegisteredInSemester(newUsersToAdd);
//            //System.out.println(newUsersToAdd.toString());
//            //System.out.println(semester.getUsersRegisteredInSemester().size());
//            semester.getUsersRegisteredInSemester().addAll(newUsersToAdd);
//            Semester sem = semesterRepository.save(semester);
//            System.out.println(sem);
//            return sem;
//        }).orElseThrow(()-> new SemesterNotFoundException(semesterId));

        Semester newSem = semesterRepository.findById(semesterId).orElseThrow(() ->new SemesterNotFoundException(semesterId));

        List<User> newUsersToAdd = new ArrayList<>();
        userIdList.forEach(userId ->{
                User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
                if(user.getRole()!= UserRole.STUDENT)
                    throw new StudentNotFoundException(userId);

                newUsersToAdd.add(user);
            });
        newSem.getUsersRegisteredInSemester().addAll(newUsersToAdd);
        Semester newSem2 =semesterRepository.save(newSem);
        System.out.println(newSem2);
        return newSem2;
    }

    @Override
    public void deleteSemesterById(long id) {
        semesterRepository.deleteById(id);
    }

}
