package dev.araga.controllers;

import com.google.gson.Gson;
import dev.araga.daos.EmployeeDaoPostgres;
import dev.araga.daos.ManagerDaoPostgres;
import dev.araga.entities.Employee;
import dev.araga.entities.Manager;
import dev.araga.services.EmployeeService;
import dev.araga.services.EmployeeServiceImpl;
import dev.araga.services.ManagerService;
import dev.araga.services.ManagerServiceImpl;
import io.javalin.http.Handler;

import java.util.Set;

public class EmployeeController {

    private EmployeeService eserv = new EmployeeServiceImpl(new EmployeeDaoPostgres());
    private ManagerService mserv = new ManagerServiceImpl(new ManagerDaoPostgres());


    public Handler getAllEmployeeHandler = ctx ->{
        Set<Employee> eAll = this.eserv.getAllEmployees();
        Gson gson = new Gson();
        String employeeJson = gson.toJson(eAll);
        ctx.result(employeeJson);
        ctx.status(200);
    };


    public Handler getEmployeeByIdHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        int id2 = Integer.parseInt(ctx.pathParam("id2"));
        Manager manager = this.mserv.getManagerById(id);
        Employee employee = this.eserv.getEmployeeById(id2);
        if((manager == null) || (employee == null)){
            ctx.result("Manager or employee was not found");
            ctx.status(404);
        }else if (employee.getM_id() == manager.getManagerId()){
            Gson gson = new Gson();
            String accountJson = gson.toJson(employee);
            ctx.result(accountJson);
            ctx.status(200);
        } else {
            ctx.result("Manager does not manage that particular employee");
        }
    };

    public Handler createEmployeeHandler = (ctx) ->{
        String body = ctx.body();
        Gson gson = new Gson();
        Employee e = gson.fromJson(body, Employee.class);
        this.eserv.createEmployee(e);
        String json = gson.toJson(e);
        ctx.result(json);
        ctx.status(201);
    };


    public Handler updateEmployeeHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        int id2 = Integer.parseInt(ctx.pathParam("id2"));
        Employee e = this.eserv.getEmployeeById(id2);
        Manager m = this.mserv.getManagerById(id);

        if(e == null || m == null){
            ctx.result("Manager or employee was not found");
            ctx.status(404);
        }else if (e.getM_id() == m.getManagerId()){
            String body = ctx.body();
            Gson gson = new Gson();
            Employee employee = gson.fromJson(body, Employee.class);
            employee.setEmployeeId(id);
            this.eserv.updateEmployee(employee);
        }else {
            ctx.result("Manager does not manage that particular employee");
        }
    };

    public Handler deleteEmployeeHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        int id2 = Integer.parseInt(ctx.pathParam("id2"));
        Manager manager = this.mserv.getManagerById(id);
        Employee employee = this.eserv.getEmployeeById(id2);
        boolean result = false;
        if(manager == null || employee == null){
            ctx.result("Manager or employee was not found");
            ctx.status(404);
            //ctx.result("Could not delete");
        }else if (manager.getManagerId() == employee.getM_id()){
            this.eserv.deleteEmployeeById(id);
            ctx.result("Employee was deleted");
        }else {
            ctx.result("Manager does not manage that particular employee");
        }

    };
}


