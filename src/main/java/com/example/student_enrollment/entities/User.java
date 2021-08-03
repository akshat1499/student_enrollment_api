package com.example.student_enrollment.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private  Date dob;
    private String address;
    private int contact;
    private String role;
    private Date joinDate;
    private Date leaveDate;
    private String status;

    @ManyToOne()
    @JoinColumn(name="department_id")
    private Department department;


    @OneToMany(mappedBy = "salary")
    private List<User> user;


    //only applicable for user_role=students
    @ManyToMany
    @JoinTable(
            name = "user_semester",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "semester_id"))
    List<Semester> semestersEnrolledByUser;



    @OneToMany(mappedBy = "userSCU", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CourseTaughtByInSemester> courseTaughtByInSemesters;


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User(){

    }

    public User(String name, Date dob, String address, int contact, String role, Date joinDate, Date leaveDate, String status, Department department) {
        this.name = name;
        this.dob= dob;
        this.address = address;
        this.contact = contact;
        this.role = role;
        this.joinDate = joinDate;
        this.leaveDate = leaveDate;
        this.status = status;
        this.department = department;
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

    public String getRole() {
        return role;
    }

    public void setRole(String type) {
        this.role = type;
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
