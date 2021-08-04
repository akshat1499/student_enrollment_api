package com.example.student_enrollment.entities;

import com.example.student_enrollment.utillities.Status;
import com.example.student_enrollment.utillities.UserRole;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="usertable")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Date dob;
    private String address;
    private String contact;
    private UserRole role;
    private Date joinDate;
    private Date leaveDate;
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id")
    private Department departmentUser;


    @OneToMany(mappedBy = "user")
    private List<Salary> salaries;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;

    //only applicable for user_role=students
    @ManyToMany
    @JoinTable(
            name = "usertable_semester",
            joinColumns = @JoinColumn(name = "usertable_id"),
            inverseJoinColumns = @JoinColumn(name = "semester_id"))
    List<Semester> semestersEnrolledByUser;



//    @OneToMany(mappedBy = "userSCU", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private List<CourseTaughtByInSemester> courseTaughtByInSemesters;


    public Department getDepartmentUser() {
        return departmentUser;
    }

    public void setDepartmentUser(Department department) {
        this.departmentUser = department;
    }


    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }

    public List<Semester> getSemestersEnrolledByUser() {
        return semestersEnrolledByUser;
    }

    public void setSemestersEnrolledByUser(List<Semester> semestersEnrolledByUser) {
        this.semestersEnrolledByUser = semestersEnrolledByUser;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    //    public List<CourseTaughtByInSemester> getCourseTaughtByInSemesters() {
//        return courseTaughtByInSemesters;
//    }
//
//    public void setCourseTaughtByInSemesters(List<CourseTaughtByInSemester> courseTaughtByInSemesters) {
//        this.courseTaughtByInSemesters = courseTaughtByInSemesters;
//    }
}
