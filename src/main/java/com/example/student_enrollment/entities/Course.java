package com.example.student_enrollment.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long fee;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "usertable_id")
    private User instructor;

    public void setDepartment(Department department) {
        this.department = department;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="department_id")
    private Department department;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "coursesOffered")
    List<Semester> semesterList;

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Department getDepartment() {
        return department;
    }

    public List<Semester> getSemesterList() {
        return semesterList;
    }

    public void setSemesterList(List<Semester> semesterList) {
        this.semesterList = semesterList;
    }

//    public List<CourseTaughtByInSemester> getCourseTaughtByInSemesters() {
//        return courseTaughtByInSemesters;
//    }

//    public void setCourseTaughtByInSemesters(List<CourseTaughtByInSemester> courseTaughtByInSemesters) {
//        this.courseTaughtByInSemesters = courseTaughtByInSemesters;
//    }

//    @OneToMany(mappedBy = "courseSCU", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private List<CourseTaughtByInSemester> courseTaughtByInSemesters;
}
