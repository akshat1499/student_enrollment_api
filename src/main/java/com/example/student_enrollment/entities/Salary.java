package com.example.student_enrollment.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "salary")
public class Salary {

    @Id
    @GeneratedValue
    private long id;
    private Date created;
    private Date periodFrom;
    private Date periodTo;
    private int amount;

    @ManyToOne()
    @JoinColumn(name="usertable_id")
    private User user;

    public Salary(){

    }

    public Salary(Date created, Date periodFrom, Date periodTo, int amount, User user) {
        this.created = created;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
        this.amount = amount;
        this.user = user;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
