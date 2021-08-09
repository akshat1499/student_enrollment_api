package com.example.student_enrollment.pojos;

import com.example.student_enrollment.exceptions.InvalidValueException;
import com.example.student_enrollment.utillities.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SemesterPOJO {
    private String name;

    private String  startDate;

    private String endDate;

    private Status status;


    public static void validate(SemesterPOJO newSemester) throws InvalidValueException{
        String regex = "\\d+";
        if(newSemester.getName().isEmpty() || newSemester.getName().matches(regex) ||newSemester.getName().substring(0,1).matches(regex))
            throw new InvalidValueException("name");

        else if(!isValidDate(newSemester.getStartDate())){
            throw new InvalidValueException("start date in format yyyy-MM-dd HH:mm:ss");
        }
        else if(!isValidDate(newSemester.getEndDate())){
            throw new InvalidValueException("start date in format yyyy-MM-dd HH:mm:ss");
        }
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String  getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
