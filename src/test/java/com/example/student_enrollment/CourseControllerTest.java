package com.example.student_enrollment;


import com.example.student_enrollment.entities.Course;
import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.pojos.CoursePOJO;
import com.example.student_enrollment.repositories.CourseRepository;
import com.example.student_enrollment.services.CourseService;
import com.example.student_enrollment.utillities.DateGenerator;
import com.example.student_enrollment.utillities.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.*;



import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CourseControllerTest {

    private static final ObjectMapper om = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;


    @Test
    public void saveCourse_success() throws Exception{
        Date createdOn = DateGenerator.getDateFromString("2021-09-08 22:22:22");
        Date updatedOn= DateGenerator.getDateFromString("2021-09-08 22:22:22");
        Department d1 =  new Department("Dept1", Status.ACTIVE);
        d1.setId(1L);
        d1.setCreatedOn(createdOn);
        d1.setUpdatedOn(updatedOn);

        Course course = new Course();
        course.setStatus(Status.ACTIVE);
        course.setCreatedOn(createdOn);
        course.setFee(30000L);
        course.setName("Course1");
        course.setUpdatedOn(updatedOn);
        course.setInstructor(null);
        course.setDepartment(d1);
        course.setId(1L);
        course.setSemesterList(null);


        CoursePOJO coursePOJO = new CoursePOJO();
        coursePOJO.setFee(30000L);
        coursePOJO.setName("Course1");
        coursePOJO.setStatus(Status.ACTIVE);
        coursePOJO.setDeptId(1L);
        coursePOJO.setInstId(1L);

        Mockito.when(courseRepository.save(course)).thenReturn(course);
        Mockito.when(courseService.saveCourse(ArgumentMatchers.any())).thenReturn(course);

        //TODO: Why argument Matachers.any and not COurseOJO


        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.om.writeValueAsString(coursePOJO));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name").value("Course1"));

    }


}

