package com.example.student_enrollment.entities;

import com.example.student_enrollment.utillities.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="department")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Department implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Status status;

    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

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


//    @OneToMany(mappedBy = "departmentUser", fetch = FetchType.LAZY)
//    @JsonIgnoreProperties({"departmentUser","salaries","courses","semestersEnrolledByUser"})
//    private List<User> user;
//
//    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
//    @JsonIgnoreProperties({"instructor","department","semesterList"})
//    private List<Course> coursesList;



    public Department(){

    }

    public Department(String name, Status status) {
        this.name = name;
        this.status = status;
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
