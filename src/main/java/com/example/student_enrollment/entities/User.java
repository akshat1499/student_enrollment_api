package com.example.student_enrollment.entities;

import com.example.student_enrollment.utillities.Status;
import com.example.student_enrollment.utillities.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="usertable")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
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

    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id")
    @JsonIgnoreProperties({"user", "coursesList"})
    private Department departmentUser;

    //only applicable for user_role=students
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.PERSIST})
    @JoinTable(
            name = "usertable_semester",
            joinColumns = @JoinColumn(name = "usertable_id"),
            inverseJoinColumns = @JoinColumn(name = "semester_id"))
    @JsonIgnoreProperties({"coursesOffered"})
    List<Semester> semestersEnrolledByUser;



    public Department getDepartmentUser() {
        return departmentUser;
    }

    public void setDepartmentUser(Department department) {
        this.departmentUser = department;
    }


    public List<Semester> getSemestersEnrolledByUser() {
        return semestersEnrolledByUser;
    }

    public void setSemestersEnrolledByUser(List<Semester> semestersEnrolledByUser) {
        this.semestersEnrolledByUser = semestersEnrolledByUser;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", role=" + role +
                ", joinDate=" + joinDate +
                ", leaveDate=" + leaveDate +
                ", status=" + status +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", departmentUser=" + departmentUser +
                ", semestersEnrolledByUser=" + semestersEnrolledByUser +
                '}';
    }
}
