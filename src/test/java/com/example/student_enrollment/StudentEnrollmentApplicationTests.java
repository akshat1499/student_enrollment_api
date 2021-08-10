package com.example.student_enrollment;

import com.example.student_enrollment.entities.Department;
import com.example.student_enrollment.services.DepartmentService;
import com.example.student_enrollment.utillities.Status;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@AutoConfigureMockMvc
class StudentEnrollmentApplicationTests {

//    @Autowired
//    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }
//
//    @MockBean
//    private DepartmentService departmentService;
//
//    Date createdOn;
//    Date updatedOn;
//
//    {
//        try {
//            createdOn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-08-09 22:22:22");
//            updatedOn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-08-09 22:22:22");
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//
//    Department d1=new Department(Integer.toUnsignedLong(1),"Dept1", Status.ACTIVE, createdOn,updatedOn);
//    Department d2=new Department(Integer.toUnsignedLong(2),"Dept2", Status.ACTIVE, createdOn,updatedOn);
//    List<Department> departments = new ArrayList<>(Arrays.asList(d1,d2));
//
//    @Test
//    public void getAllDepartments_success() throws Exception{
//
//
//
//
//        Mockito.when(departmentService.getAllDepartments()).thenReturn(departments);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/departments")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$",hasSize(3)))
//                .andExpect((ResultMatcher) jsonPath("$[2].name",is("Dept2")));
//
//    }

}
