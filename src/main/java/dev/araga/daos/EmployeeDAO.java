package dev.araga.daos;

import dev.araga.entities.Employee;

import java.util.Set;

public interface EmployeeDAO {

    //CREATE
    Employee createEmployee (Employee employee);

    //READ
    Set<Employee> getAllEmployee();
    Employee getEmployeeById(int id);

    //UPDATE
    Employee updateEmployee(Employee employee);

    //DELETE
    boolean deleteEmployeeById(int id);

}
