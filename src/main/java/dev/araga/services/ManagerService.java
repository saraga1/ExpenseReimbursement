package dev.araga.services;

import dev.araga.entities.Manager;

import java.util.Set;

public interface ManagerService {

    Manager createManager(Manager manager);

    Set<Manager> getAllManagers();
//    Set<Manager> getManagerByName(String name);

    Manager getManagerById(int id);

    Manager updateManager(Manager manager);

    boolean deleteManagerById(int id);
}
