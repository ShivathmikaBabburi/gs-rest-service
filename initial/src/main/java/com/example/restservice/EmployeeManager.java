package com.example.restservice;


import org.springframework.stereotype.Repository;

@Repository
public class EmployeeManager {

    private static Employees employees = new Employees();

    static {
        // Initialize with sample employees
        employees.getEmployeeList()
                .add(new Employee("1", "Prem", "Tiwari", "prem@gmail.com","Developer"));
        employees.getEmployeeList()
                .add(new Employee("2", "Vikash", "Kumar", "vikash@gmail.com","Engineer"));
        employees.getEmployeeList()
                .add(new Employee("3", "Ritesh", "Ojha", "ritesh@gmail.com","Traniee"));
    }

    // Retrieve all employees
    public Employees getAllEmployees() {
        return employees;
    }

    public void addNewEmployee(Employee employee) {
        employees.getEmployeeList().add(employee);
    }
}
