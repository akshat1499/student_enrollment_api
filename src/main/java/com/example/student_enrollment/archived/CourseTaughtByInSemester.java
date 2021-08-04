package com.example.student_enrollment.archived;
import com.example.student_enrollment.entities.Course;
import com.example.student_enrollment.entities.Semester;
import com.example.student_enrollment.entities.User;

import javax.persistence.*;

@Entity
@Table(name ="semester_course_usertable")
public class CourseTaughtByInSemester {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "semester_id")
    private Semester semesterSCU;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "course_id")
    private Course courseSCU;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "usertable_id")
    private User userSCU;

}
