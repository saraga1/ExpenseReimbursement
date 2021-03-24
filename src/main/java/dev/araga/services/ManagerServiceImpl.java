package dev.araga.services;

import dev.araga.daos.ManagerDaoPostgres;
import dev.araga.entities.Manager;
import org.apache.log4j.Logger;

import java.util.Set;

public class ManagerServiceImpl implements ManagerService {

    private static Logger logger = Logger.getLogger(ManagerServiceImpl.class.getName()); // when the logger writes

    private ManagerDaoPostgres mdao;

    public ManagerServiceImpl(ManagerDaoPostgres mdao){
        this.mdao = mdao;
    }

    @Override
    public Manager createManager(Manager manager) {
        return this.mdao.createManager(manager);
    }

    @Override
    public Set<Manager> getAllManagers() {
        return this.mdao.getAllManagers();
    }

//    @Override
//    public Set<Manager> getManagerByName(String name) {
//        return this.mdao;
//    }

    @Override
    public Manager getManagerById(int id) {
        return this.mdao.getManagerById(id);
    }

    @Override
    public Manager updateManager(Manager manager) {
        return this.mdao.updateManager(manager);
    }

    @Override
    public boolean deleteManagerById(int id) {
        return this.mdao.deleteManagerById(id);
    }
}
