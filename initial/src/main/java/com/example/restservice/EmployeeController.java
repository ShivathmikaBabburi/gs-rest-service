package com.example.restservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeManager employeeManager;

    @GetMapping("/employees")
    public Employees getEmployees(){
        return employeeManager.getAllEmployees();
    }
    @PostMapping("/employee")
    public String postEmployee(@RequestBody Employee employee){
        employeeManager.addNewEmployee(employee);
        return "employee inserted successfully";
    }
}
