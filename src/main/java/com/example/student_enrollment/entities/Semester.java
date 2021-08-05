package com.example.student_enrollment.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="semester")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Semester {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    private Date startDate;
    private Date endDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    @JoinTable(
            name = "semester_course",
            joinColumns = @JoinColumn(name = "semester_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @JsonIgnoreProperties({"instructor","department","semesterList"})
    List<Course> coursesOffered;

    @ManyToMany(mappedBy = "semestersEnrolledByUser", fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JsonIgnoreProperties({"semestersEnrolledByUser","departmentUser","salaries","courses"})
    List<User> usersRegisteredInSemester;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Semester(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Semester(){

    }

    public List<Course> getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(List<Course> coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    public List<User> getUsersRegisteredInSemester() {
        return usersRegisteredInSemester;
    }

    public void setUsersRegisteredInSemester(List<User> usersRegisteredInSemester) {
        this.usersRegisteredInSemester = usersRegisteredInSemester;
    }
    public long getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

//    public List<CourseTaughtByInSemester> getCourseTaughtByInSemesters() {
//        return courseTaughtByInSemesters;
//    }
//
//    public void setCourseTaughtByInSemesters(List<CourseTaughtByInSemester> courseTaughtByInSemesters) {
//        this.courseTaughtByInSemesters = courseTaughtByInSemesters;
//    }
//    @OneToMany(mappedBy = "semesterSCU", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private List<CourseTaughtByInSemester> courseTaughtByInSemesters;


}
