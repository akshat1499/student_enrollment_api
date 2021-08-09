package com.example.student_enrollment.pojos;

import com.example.student_enrollment.exceptions.InvalidValueException;
import com.example.student_enrollment.utillities.Status;

import java.sql.DataTruncation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryPOJO {
    private String periodFrom;
    private String periodTo;
    private Long amount;
    private Long userId;
    private Status status;
    private Date createdOn;
    private Date updatedOn;

    public SalaryPOJO(String periodFrom, String periodTo, Long amount, Long userId, Status status, Date createdOn, Date updatedOn) {
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
        this.amount = amount;
        this.userId = userId;
        this.status = status;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public static void validate(SalaryPOJO newSalary) throws InvalidValueException{
        String regex = "\\d+";
        if(!isValidDate(newSalary.getPeriodFrom())){
            throw new InvalidValueException("Period From in format yyyy-MM-dd HH:mm:ss");
        }
        else if(!isValidDate(newSalary.getPeriodTo())){
            throw new InvalidValueException("Period To in format yyyy-MM-dd HH:mm:ss");
        }
        else if(newSalary.getAmount()<0)
            throw new InvalidValueException("amount");
        else if(newSalary.getUserId()<0)
            throw new InvalidValueException("userId");
    }


    public static boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
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

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(String periodFrom) {
        this.periodFrom = periodFrom;
    }

    public String getPeriodTo() {
        return periodTo;
    }

    public void setPeriodTo(String periodTo) {
        this.periodTo = periodTo;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
