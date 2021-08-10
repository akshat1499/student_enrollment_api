package com.example.student_enrollment.pojos;

import com.example.student_enrollment.exceptions.InvalidValueException;
import com.example.student_enrollment.utillities.Status;

public class DepartmentPOJO {

    private String name;
    private Status status;

    public DepartmentPOJO(){

    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static void validate (DepartmentPOJO newDepartment) throws InvalidValueException {
        String regex = "\\d+";

        if(newDepartment.getName().isEmpty() || newDepartment.getName().matches(regex)||newDepartment.getName().substring(0,1).matches(regex))
            throw new InvalidValueException("name");


    }

    public DepartmentPOJO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
