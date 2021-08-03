package com.example.student_enrollment.entities;

import com.example.student_enrollment.utillities.UserRole;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="usertable")
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Date dob;
    private String address;
    private int contact;
    private UserRole role;
    private Date joinDate;
    private Date leaveDate;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id")
    private Department departmentUser;


    @OneToMany(mappedBy = "user")
    private List<Salary> salaries;


    //only applicable for user_role=students
    @ManyToMany
    @JoinTable(
            name = "usertable_semester",
            joinColumns = @JoinColumn(name = "usertable_id"),
            inverseJoinColumns = @JoinColumn(name = "semester_id"))
    List<Semester> semestersEnrolledByUser;



    @OneToMany(mappedBy = "userSCU", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CourseTaughtByInSemester> courseTaughtByInSemesters;


    public Department getDepartmentUser() {
        return departmentUser;
    }

    public void setDepartmentUser(Department department) {
        this.departmentUser = department;
    }

    public User(){

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

    public List<CourseTaughtByInSemester> getCourseTaughtByInSemesters() {
        return courseTaughtByInSemesters;
    }

    public void setCourseTaughtByInSemesters(List<CourseTaughtByInSemester> courseTaughtByInSemesters) {
        this.courseTaughtByInSemesters = courseTaughtByInSemesters;
    }

    public User(String name, Date dob, String address, int contact, UserRole role, Date joinDate, Date leaveDate, String status, Department departmentUser) {
        this.name = name;
        this.dob= dob;
        this.address = address;
        this.contact = contact;
        this.role = role;
        this.joinDate = joinDate;
        this.leaveDate = leaveDate;
        this.status = status;
        this.departmentUser = departmentUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
