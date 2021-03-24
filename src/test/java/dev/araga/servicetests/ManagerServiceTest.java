package dev.araga.servicetests;

import dev.araga.daos.ManagerDAO;
import dev.araga.daos.ManagerDaoPostgres;
import dev.araga.entities.Manager;
import dev.araga.services.ManagerService;
import dev.araga.services.ManagerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class ManagerServiceTest {

    private static ManagerService mdao = new ManagerServiceImpl(new ManagerDaoPostgres());
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

}
