package com.example.student_enrollment.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="semester")
public class Semester {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Date startDate;
    private Date endDate;

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
}
