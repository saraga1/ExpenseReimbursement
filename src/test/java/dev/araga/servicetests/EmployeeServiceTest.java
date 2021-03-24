package dev.araga.servicetests;


import dev.araga.daos.EmployeeDaoPostgres;
import dev.araga.entities.Employee;
import dev.araga.services.EmployeeService;
import dev.araga.services.EmployeeServiceImpl;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceTest {


    private static EmployeeService eserv = new EmployeeServiceImpl(new EmployeeDaoPostgres());
    private static Employee testEmployee = null;


    @Test
    @Order(1)
    void create_employee(){
        Employee e1 = new Employee(0, "Sam", "Araga", "username2021", "pass123",1);
        eserv.createEmployee(e1);
        System.out.println(e1);
        testEmployee = e1;
        System.out.println(testEmployee.getEmployeeId());
        Assertions.assertNotEquals(0, testEmployee.getEmployeeId());
    }

    @Test
    @Order(2)
    void get_employee_by_id(){
        Employee e1 = new Employee(0, "Sam", "Araga", "username2021", "pass123",1);
        eserv.createEmployee(e1);
        System.out.println(e1);
        testEmployee = e1;
        int id = testEmployee.getEmployeeId();
        System.out.println(id);
        Assertions.assertEquals(testEmployee.getFname(), e1.getFname());


    }



}
