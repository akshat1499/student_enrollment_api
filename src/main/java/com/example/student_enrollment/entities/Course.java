package com.example.student_enrollment.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int fee;

    @ManyToOne()
    @JoinColumn(name="department_id")
    private Department department;

    @ManyToMany(mappedBy = "coursesOffered")
    List<Semester> semesterList;

    @OneToMany(mappedBy = "courseSCU", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CourseTaughtByInSemester> courseTaughtByInSemesters;
}
