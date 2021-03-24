package dev.araga.servicetests;

import dev.araga.daos.EmployeeDaoPostgres;
import dev.araga.entities.Employee;
import dev.araga.services.EmployeeService;
import dev.araga.services.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceMockitoTest {


    @Mock
    EmployeeDaoPostgres emdao = null;

    private EmployeeService employeeService = null;

    @BeforeEach
    void setup(){
        Employee e1 = new Employee(1, "Sam", "Araga", "username2021", "pass123",1);
        Employee e2 = new Employee(2, "Sam", "Araga", "username2021", "pass123",1);
        Employee e3 = new Employee(3, "Sam", "Araga", "username2021", "pass123",1);
        Set<Employee> allEmployee = emdao.getAllEmployee();

        allEmployee.add(e1);
        allEmployee.add(e2);
        allEmployee.add(e3);

        Mockito.when(emdao.getAllEmployee()).thenReturn(allEmployee);
        this.employeeService = new EmployeeServiceImpl(this.emdao);

    }
    @Test
    void get_all_employee(){
        Set<Employee>employee = this.employeeService.getAllEmployees();
        System.out.println(employee);
        Assertions.assertTrue(employee.size()>2);

    }

}
