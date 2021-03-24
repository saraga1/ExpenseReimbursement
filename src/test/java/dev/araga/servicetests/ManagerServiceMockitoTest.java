package dev.araga.servicetests;

import dev.araga.daos.ManagerDaoPostgres;
import dev.araga.entities.Employee;
import dev.araga.entities.Manager;
import dev.araga.services.EmployeeServiceImpl;
import dev.araga.services.ManagerService;
import dev.araga.services.ManagerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ManagerServiceMockitoTest {

    @Mock
    ManagerDaoPostgres mdao = null;

    private ManagerService managerService = null;

    @BeforeEach
    void setUp(){
        Manager m1 = new Manager(0, "Sam", "Araga", "username2021", "pass123");
        Manager m2 = new Manager(0, "Sam", "Araga", "username2021", "pass123");
        Manager m3 = new Manager(0, "Sam", "Araga", "username2021", "pass123");
        Set<Manager> allManager = mdao.getAllManagers();
        allManager.add(m1);
        allManager.add(m2);
        allManager.add(m3);



        Mockito.when(mdao.getAllManagers()).thenReturn(allManager);
        this.managerService = new ManagerServiceImpl(this.mdao);

    }

    @Test
    void get_all_managers(){
        Set<Manager>manager = this.managerService.getAllManagers();
        System.out.println(manager);
        Assertions.assertTrue(manager.size()>2);
    }


}
