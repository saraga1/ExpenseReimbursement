package dev.araga.services;

import dev.araga.entities.Employee;

import java.util.Set;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Set<Employee> getAllEmployees();
//    Set<Employee> getAllEmployeeByStatus();
    Employee getEmployeeById(int id);

//    Employee getEmployeeStatusById(int id);

//    Employee updateEmployeeStatus(Employee employee);

    Employee updateEmployee(Employee employee);

    boolean deleteEmployeeById(int id);

}
