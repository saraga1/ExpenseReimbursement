package dev.araga.daotests;


import dev.araga.daos.ManagerDAO;
import dev.araga.daos.ManagerDaoPostgres;
import dev.araga.entities.Expenses;
import dev.araga.entities.Manager;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ManagerDaoTest {

    private static ManagerDAO mdao = new ManagerDaoPostgres();
    private static Manager testManager = null;


    @Test
    @Order(1)
    void create_manager() {
        Manager manager = new Manager(0, "Sam", "Araga", "username2021", "pass123");
        mdao.createManager(manager);
        System.out.println(manager);
        testManager = manager;
        System.out.println(testManager.getManagerId());
        Assertions.assertNotEquals(0, testManager.getManagerId());
    }

    @Test
    @Order(2)
    void get_manager_by_id() {
        Manager manager = new Manager(0, "Sam", "Araga", "username2021", "pass123");
        mdao.createManager(manager);
        System.out.println(manager);
        testManager = manager;
        int id = testManager.getManagerId();
        System.out.println(id);
        Assertions.assertEquals(testManager.getFname(), manager.getFname());

    }

    @Test
    @Order(3)
    void update_manager() {

        Manager manager = mdao.getManagerById(testManager.getManagerId());
        manager.setFname("Bob");
        System.out.println(manager.getFname());
        mdao.updateManager(manager);
        System.out.println(mdao);

        Manager updatesManager = mdao.getManagerById(testManager.getManagerId());
        System.out.println(updatesManager.getFname());
        Assertions.assertEquals("Bob", updatesManager.getFname());

    }

    @Test
    @Order(4)
    void get_all_manager(){
        Manager m1 = new Manager(0, "Sam", "Araga", "username2021", "pass123");
        Manager m2 = new Manager(0, "Sam", "Araga", "username2021", "pass123");
        Manager m3 = new Manager(0, "Sam", "Araga", "username2021", "pass123");
        Set<Manager> allManager = mdao.getAllManagers();
        allManager.add(mdao.createManager(m1));
        allManager.add(mdao.createManager(m2));
        allManager.add(mdao.createManager(m3));

        Assertions.assertTrue(allManager.size()>2);
        System.out.println(allManager.size()+"hkkhlkjhlkhl");

    }

    @Test
    @Order(5)
    void delete_manager_by_id(){
        int id = testManager.getManagerId();
        boolean result = mdao.deleteManagerById(id);
        Assertions.assertTrue(result);
    }



}