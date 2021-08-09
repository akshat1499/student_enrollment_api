package com.example.student_enrollment.pojos;

import com.example.student_enrollment.exceptions.InvalidValueException;
import com.example.student_enrollment.utillities.Status;

public class CoursePOJO {

    private String name;
    private Long fee;
    private Long instId;
    private Long deptId;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



    public static void validate (CoursePOJO newCourse) throws InvalidValueException {
        String regex = "\\d+";

        if(newCourse.getName().isEmpty() || newCourse.getName().matches(regex)||newCourse.getName().substring(0,1).matches(regex))
            throw new InvalidValueException("name");

        else if(newCourse.fee<0)
            throw new InvalidValueException("fee");
        else if(newCourse.instId<1)
            throw new InvalidValueException("Instructor Id");
        else if(newCourse.deptId<1)
            throw new InvalidValueException("Department Id");


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

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
