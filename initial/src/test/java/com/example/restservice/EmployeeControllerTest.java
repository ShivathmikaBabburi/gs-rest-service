package com.example.restservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeManager employeeManager;

    @Test
    public void testGetEmployees() throws Exception {
        // Arrange
        Employee employee1 = new Employee("1", "Prem", "Tiwari", "prem@gmail.com", "Developer");
        Employee employee2 = new Employee("2", "Vikash", "Kumar", "vikash@gmail.com", "Engineer");
        Employees employees = new Employees();
        employees.setEmployeeList(Arrays.asList(employee1, employee2));

        when(employeeManager.getAllEmployees()).thenReturn(employees);

        // Act & Assert
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeList[0].employee_id").value("1"))
                .andExpect(jsonPath("$.employeeList[0].first_name").value("Prem"))
                .andExpect(jsonPath("$.employeeList[1].employee_id").value("2"))
                .andExpect(jsonPath("$.employeeList[1].first_name").value("Vikash"));

        verify(employeeManager, times(1)).getAllEmployees();
    }

    @Test
    public void testPostEmployee() throws Exception {
        // Arrange
        Employee newEmployee = new Employee("3", "Ritesh", "Ojha", "ritesh@gmail.com", "Trainee");
        doNothing().when(employeeManager).addNewEmployee(newEmployee);

        // Act & Assert
        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newEmployee)))
                .andExpect(status().isOk())
                .andExpect(content().string("employee inserted successfully"));

        verify(employeeManager, times(1)).addNewEmployee(any(Employee.class));
    }

    // Helper method to convert objects to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
