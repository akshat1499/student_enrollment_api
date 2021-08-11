package com.example.student_enrollment.pojos;

import com.example.student_enrollment.exceptions.InvalidValueException;
import com.example.student_enrollment.utillities.Status;
import com.example.student_enrollment.utillities.UserRole;
import org.yaml.snakeyaml.util.EnumUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserPOJO {
    private String name;
    private String dob;
    private String address;
    private String contact;
    private String role;
    private String joinDate;
    private Long deptId;




    //ToDo: Give all errors at once
    public static void validate(UserPOJO newUser) throws InvalidValueException {
        String regex = "\\d+";
        String error="";
        if(newUser==null){
            throw new InvalidValueException("new user. No detail found");
        }
         if(newUser.getAddress().isEmpty()) error += "address, ";
         if (newUser.getContact().length()!=10 || !newUser.getContact().matches(regex)){
            error+="contact : only 10 digits are allowed, ";
        }
         if(newUser.getDeptId()<1)
            error+="department id, ";
        if(newUser.getName().isEmpty() || newUser.getName().matches(regex) ||newUser.getName().substring(0,1).matches(regex))
            error+="name : should not be empty or begin with a digit, ";
        if(!isValidDate(newUser.getJoinDate()))
            error+="join date in format yyyy-MM-dd HH:mm:ss, ";
        if(!isValidDate(newUser.getDob()))
            error+="date of birth in format yyyy-MM-dd HH:mm:ss, ";
        if (!(newUser.getRole().equalsIgnoreCase("STUDENT")||
                 newUser.getRole().equalsIgnoreCase("LECTURER")))
            error+="user role. only STUDENT and LECTURER are valid inputs.";

        if(!error.isEmpty()) throw new InvalidValueException(error);

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
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
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

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
