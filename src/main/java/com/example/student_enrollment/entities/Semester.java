package com.example.student_enrollment.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="semester")
public class Semester {
    @Id
    @GeneratedValue
    private long id;
    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<CourseTaughtByInSemester> getCourseTaughtByInSemesters() {
        return courseTaughtByInSemesters;
    }

    public void setCourseTaughtByInSemesters(List<CourseTaughtByInSemester> courseTaughtByInSemesters) {
        this.courseTaughtByInSemesters = courseTaughtByInSemesters;
    }

    private Date startDate;
    private Date endDate;

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

    @ManyToMany
    @JoinTable(
            name = "semester_course",
            joinColumns = @JoinColumn(name = "semester_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    List<Course> coursesOffered;

    @ManyToMany(mappedBy = "semestersEnrolledByUser")
    List<User> usersRegisteredInSemester;


    @OneToMany(mappedBy = "semesterSCU", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CourseTaughtByInSemester> courseTaughtByInSemesters;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
