package com.example.student_enrollment.utillities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateGenerator {


    public static Date getDateFromString(String s) {
        Date date = null;
        try{
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
        }catch (Exception e){
        }
        return date;
    }
}
