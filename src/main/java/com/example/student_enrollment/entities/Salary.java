package com.example.student_enrollment.entities;

import com.example.student_enrollment.utillities.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "salary")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Salary {

    @Id
    @GeneratedValue
    private long id;
    private Date created;
    private Date periodFrom;
    private Date periodTo;
    private Long amount;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name="usertable_id")
    @JsonIgnoreProperties({"departmentUser","salaries","courses","semestersEnrolledByUser"})
    private User user;

    public Salary(){

    }

    public Salary(Date created, Date periodFrom, Date periodTo, Long amount,Status status) {
        this.created = created;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
        this.amount = amount;
        this.status=status;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(Date periodFrom) {
        this.periodFrom = periodFrom;
    }

    public Date getPeriodTo() {
        return periodTo;
    }

    public void setPeriodTo(Date periodTo) {
        this.periodTo = periodTo;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
