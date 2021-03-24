package dev.araga.daotests;

import dev.araga.daos.EmployeeDAO;
import dev.araga.daos.EmployeeDaoPostgres;
import dev.araga.entities.Employee;
import dev.araga.entities.Manager;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTest {

    private static EmployeeDAO emdao = new EmployeeDaoPostgres();
    private static Employee testEmployee = null;



    @Test
    @Order(1)
    void create_employee() {
        Employee e1 = new Employee(0, "Sam", "Araga", "username2021", "pass123",1);
        emdao.createEmployee(e1);
        System.out.println(e1);
        testEmployee = e1;
        System.out.println(testEmployee.getEmployeeId());
        Assertions.assertNotEquals(0, testEmployee.getEmployeeId());
    }

    @Test
    @Order(2)
    void get_employee_by_id() {
        Employee e1 = new Employee(0, "Sam", "Araga", "username2021", "pass123",1);
        emdao.createEmployee(e1);
        System.out.println(e1);
        testEmployee = e1;
        int id = testEmployee.getEmployeeId();
        System.out.println(id);
        Assertions.assertEquals(testEmployee.getFname(), e1.getFname());

    }


    @Test
    @Order(3)
    void update_employee() {

        Employee employee = emdao.getEmployeeById(testEmployee.getEmployeeId());
        employee.setFname("Bob");
        System.out.println(employee.getFname());
        emdao.updateEmployee(employee);
        System.out.println(emdao);

        Employee updatesEmployee = emdao.getEmployeeById(testEmployee.getEmployeeId());
        System.out.println(updatesEmployee.getFname());
        Assertions.assertEquals("Bob", updatesEmployee.getFname());

    }


    @Test
    @Order(4)
    void get_all_manager(){
        Employee e1 = new Employee(0, "Sam", "Araga", "username2021", "pass123",1);
        Employee e2 = new Employee(0, "Sam", "Araga", "username2021", "pass123",1);
        Employee e3 = new Employee(0, "Sam", "Araga", "username2021", "pass123",1);
        Set<Employee> allEmployee = emdao.getAllEmployee();

        allEmployee.add(emdao.createEmployee(e1));
        allEmployee.add(emdao.createEmployee(e2));
        allEmployee.add(emdao.createEmployee(e3));

        Assertions.assertTrue(allEmployee.size()>2);
        System.out.println(allEmployee.size()+"hkkhlkjhlkhl");

    }


    @Test
    @Order(5)
    void delete_manager_by_id(){
        int id = testEmployee.getEmployeeId();
        boolean result = emdao.deleteEmployeeById(id);
        Assertions.assertTrue(result);
    }


}
