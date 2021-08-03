package com.example.student_enrollment.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="department")
public class Department {

    @Id
    @GeneratedValue
    private long id;
    private String name;


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
}
