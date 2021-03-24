package dev.araga.services;

import dev.araga.daos.EmployeeDaoPostgres;
import dev.araga.entities.Employee;
import org.apache.log4j.Logger;

import java.util.Set;


public class EmployeeServiceImpl implements EmployeeService {

    private static Logger logger = Logger.getLogger(EmployeeServiceImpl.class.getName()); // when the logger writes

    private EmployeeDaoPostgres edao;

    public EmployeeServiceImpl(EmployeeDaoPostgres edao){
        this.edao = edao;
    }

    @Override
    public Employee createEmployee(Employee employee) {

        return this.edao.createEmployee(employee);
    }

    @Override
    public Set<Employee> getAllEmployees() {
        return this.edao.getAllEmployee();
    }

//    @Override
//    public Set<Employee> getAllEmployeeByStatus() {
//        return null;
//    }

    @Override
    public Employee getEmployeeById(int id) {
        return this.edao.getEmployeeById(id);
    }
//
//    @Override
//    public Employee getEmployeeStatusById(int id) {
//        return null;
//    }
//
//    @Override
//    public Employee updateEmployeeStatus(Employee employee) {
//        return null;
//    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return this.edao.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        return this.edao.deleteEmployeeById(id);
    }
}
