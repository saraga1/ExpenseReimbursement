package dev.araga.daos;

import dev.araga.entities.Manager;

import java.util.Set;

public interface ManagerDAO {


    //CREATE
    Manager createManager(Manager manager);

    //READ
    Set<Manager> getAllManagers();
    Manager getManagerById(int id);

    //Update
    Manager updateManager(Manager manager);

    //DELETE
    boolean deleteManagerById(int id);





}
