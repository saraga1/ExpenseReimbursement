package dev.araga.controllers;

import com.google.gson.Gson;
import dev.araga.daos.ManagerDaoPostgres;
import dev.araga.entities.Manager;
import dev.araga.services.ManagerService;
import dev.araga.services.ManagerServiceImpl;
import io.javalin.http.Handler;

import java.util.Set;

public class ManagerController {

    private ManagerService mserv = new ManagerServiceImpl(new ManagerDaoPostgres());

    public Handler getAllManagerHandler = (ctx) -> {

        Set<Manager> mAll = this.mserv.getAllManagers();
        Gson gson = new Gson();
        String managerJson = gson.toJson(mAll);
        ctx.result(managerJson);
        ctx.status(200);
    };

    public Handler getManagerByIdHandler = ctx -> {

        int id = Integer.parseInt(ctx.pathParam("id"));
        Manager manager = this.mserv.getManagerById(id);
        if(manager == null){
            ctx.result("Client not found");
            ctx.status(404);
        }else {
            Gson gson = new Gson();
            String managerJson = gson.toJson(manager);
            ctx.result(managerJson);
            ctx.status(201);
        }
    };

    public Handler createManagerHandler = (ctx) ->{
        String body = ctx.body();
        Gson gson = new Gson();
        Manager manager = gson.fromJson(body, Manager.class);
        this.mserv.createManager(manager);
        String json = gson.toJson(manager);
        ctx.result(json);
        ctx.status(201);
    };

    public Handler updateManagerHandler = (ctx)-> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        String body = ctx.body();
        Gson gson = new Gson();
        Manager manager = gson.fromJson(body, Manager.class);
        if(manager == null){
            ctx.result("Client not find client");
            ctx.status(404);

        }else {
            manager.setManagerId(id);
            this.mserv.updateManager(manager);
        }
    };

    public Handler deleteManagerHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = this.mserv.deleteManagerById(id);
        if(deleted) {
            ctx.result("Manager was deleted");
        }else{
            ctx.result("Could not find manager");
            ctx.status(404);
        }
    };


}
