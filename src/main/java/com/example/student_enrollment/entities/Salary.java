package com.example.student_enrollment.entities;

import com.example.student_enrollment.pojos.SalaryPOJO;
import com.example.student_enrollment.utillities.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "salary")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Salary {

    @Id
    @GeneratedValue
    private long id;
    private Date periodFrom;
    private Date periodTo;
    private Long amount;
    private Status status;

    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

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

    public Salary(Date periodFrom, Date periodTo, Long amount) {
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
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

    public SalaryPOJO getSalaryPOJO(){
        SalaryPOJO salaryPOJO = new SalaryPOJO(
                this.periodFrom.toString(),
                this.periodTo.toString(),
                this.amount,
                this.id,
                this.status,
                this.createdOn,
                this.updatedOn);

        return salaryPOJO;

    }
}
