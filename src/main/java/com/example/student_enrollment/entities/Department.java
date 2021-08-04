package com.example.student_enrollment.entities;

import com.example.student_enrollment.utillities.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="department")
public class Department {

    //TODO: Department Status and created at and all classes extends Time class
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Status status;
    private Date createdOn;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }

    @OneToMany(mappedBy = "departmentUser", fetch = FetchType.LAZY)
   private List<User> user;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Course> coursesList;

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public Department(){

    }
    public Department(String name) {
        this.name = name;
    }

    public Department(long id, String name) {
        this.id = id;
        this.name = name;
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
}
