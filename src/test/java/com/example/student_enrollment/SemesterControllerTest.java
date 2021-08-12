package com.example.student_enrollment;

import com.example.student_enrollment.entities.Semester;
import com.example.student_enrollment.pojos.SemesterPOJO;
import com.example.student_enrollment.repositories.SemesterRepository;
import com.example.student_enrollment.services.SemesterService;
import com.example.student_enrollment.utillities.DateGenerator;
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

import java.util.Date;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SemesterControllerTest {

    private static final ObjectMapper om = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SemesterService semesterService;

    @MockBean
    private SemesterRepository semesterRepository;

    @Test
    public void saveCourse_success() throws Exception{
        Date startDate = DateGenerator.getDateFromString("2021-09-08 22:22:22");
        Date endDate= DateGenerator.getDateFromString("2021-09-08 22:22:22");

        Semester semester = new Semester("Sem1",startDate,endDate);
        semester.setUsersRegisteredInSemester(null);
        semester.setCoursesOffered(null);
        semester.setId(1L);


        SemesterPOJO semesterPOJO = new SemesterPOJO();
        semesterPOJO.setName("Sem1");
        semesterPOJO.setStartDate("2021-09-08 22:22:22");
        semesterPOJO.setEndDate("2021-09-08 22:22:22");



        Mockito.when(semesterRepository.save(semester)).thenReturn(semester);
        Mockito.when(semesterService.saveSemester(ArgumentMatchers.any())).thenReturn(semester);

        //ToDO:  Why ARgument Matchers.any


        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/semesters")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.om.writeValueAsString(semesterPOJO));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name").value("Sem1"));

    }
}
