package com.example.student_enrollment;

import com.example.student_enrollment.controllers.DepartmentController;
import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.services.DepartmentService;
import com.example.student_enrollment.utillities.Status;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;


    Date createdOn = new Date(2021,7,7, 22,22,22);
    Date updatedOn= new Date(2021,8,7, 22,22,22);

    Department d1 =  new Department("Dept1", Status.ACTIVE);
    Department d2 = new Department("Dept2",Status.ACTIVE);
    List<Department> departments = new ArrayList<>();

    @Before
    public void setup(){

        d1.setId(1L);
        d2.setId(2L);

        d1.setCreatedOn(createdOn);
        d1.setUpdatedOn(updatedOn);
        d2.setCreatedOn(createdOn);
        d2.setUpdatedOn(updatedOn);

        departments.add(d1);
        departments.add(d2);
    }



    @Test
    public void getAllDepartments_success() throws Exception{

        Mockito.when(departmentService.getAllDepartments()).thenReturn(departments);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/departments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect((ResultMatcher) jsonPath("$[2].name",is("Dept2")));

    }



}
