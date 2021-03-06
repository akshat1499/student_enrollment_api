package com.example.student_enrollment;


import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.pojos.DepartmentPOJO;
import com.example.student_enrollment.repositories.DepartmentRepository;
import com.example.student_enrollment.services.DepartmentService;
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
import java.util.concurrent.CompletableFuture;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DepartmentControllerTest {

    private static final ObjectMapper om = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @MockBean
    DepartmentRepository departmentRepository;


    @Test
    public void getAllDepartments_success() throws Exception{
        Date createdOn = DateGenerator.getDateFromString("2021-09-08 22:22:22");
        Date updatedOn= DateGenerator.getDateFromString("2021-09-08 22:22:22");

        Department d1 =  new Department("Dept1", Status.ACTIVE);
        Department d2 = new Department("Dept2",Status.ACTIVE);
        List<Department> departments = new ArrayList<>();
        d1.setId(1L);
        d2.setId(2L);

        d1.setCreatedOn(createdOn);
        d1.setUpdatedOn(updatedOn);
        d2.setCreatedOn(createdOn);
        d2.setUpdatedOn(updatedOn);

        departments.add(d1);
        departments.add(d2);

        Mockito.when(departmentRepository.findAll()).thenReturn(departments);
        Mockito.when(departmentService.getAllDepartments()).thenReturn(departments);


        mockMvc.perform(MockMvcRequestBuilders
                .get("/departments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[1].name").value("Dept2"));
    }

    @Test
    public void saveCourse_success() throws Exception{
        Date createdOn = DateGenerator.getDateFromString("2021-09-08 22:22:22");
        Date updatedOn= DateGenerator.getDateFromString("2021-09-08 22:22:22");
        Department d1 =  new Department("Dept1", Status.ACTIVE);
        d1.setId(1L);
        d1.setCreatedOn(createdOn);
        d1.setUpdatedOn(updatedOn);

        CompletableFuture<Department> completedFuture = CompletableFuture.completedFuture(d1);
        DepartmentPOJO departmentPOJO = new DepartmentPOJO("Dept1");

        Mockito.when(departmentRepository.save(d1)).thenReturn(d1);
        Mockito.when(departmentService.saveDepartment(ArgumentMatchers.any())).thenReturn(completedFuture);


        List<DepartmentPOJO> list = new ArrayList<>();
        list.add(departmentPOJO);



        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.om.writeValueAsString(list));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[0].name").value("Dept1"));

    }


}
