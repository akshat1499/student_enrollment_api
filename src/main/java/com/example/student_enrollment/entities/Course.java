package com.example.student_enrollment.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int fee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course(String name, int fee) {

        this.name = name;
        this.fee = fee;

    }

    public Course(){

    }
    public Course(String name, int fee, Department department) {
        this.name = name;
        this.fee = fee;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartmentById(long deptId) {
        this.department.setId(deptId);
    }

    public List<Semester> getSemesterList() {
        return semesterList;
    }

    public void setSemesterList(List<Semester> semesterList) {
        this.semesterList = semesterList;
    }

    public List<CourseTaughtByInSemester> getCourseTaughtByInSemesters() {
        return courseTaughtByInSemesters;
    }

    public void setCourseTaughtByInSemesters(List<CourseTaughtByInSemester> courseTaughtByInSemesters) {
        this.courseTaughtByInSemesters = courseTaughtByInSemesters;
    }

    @ManyToOne()
    @JoinColumn(name="department_id")
    private Department department;

    @ManyToMany(mappedBy = "coursesOffered")
    List<Semester> semesterList;

    @OneToMany(mappedBy = "courseSCU", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CourseTaughtByInSemester> courseTaughtByInSemesters;
}
