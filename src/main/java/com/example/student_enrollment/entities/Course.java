package com.example.student_enrollment.entities;

import com.example.student_enrollment.utillities.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="course")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long fee;
    private Status status;

    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;



    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "usertable_id")
    @JsonIgnoreProperties({"departmentUser","salaries","courses","semestersEnrolledByUser"})
    private User instructor;

    public void setDepartment(Department department) {
        this.department = department;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="department_id")
    @JsonIgnoreProperties({"user","coursesList"})
    private Department department;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "coursesOffered", cascade = {CascadeType.DETACH, CascadeType.PERSIST})
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }


}
