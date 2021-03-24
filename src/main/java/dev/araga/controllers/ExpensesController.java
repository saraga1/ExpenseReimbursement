package dev.araga.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dev.araga.daos.EmployeeDaoPostgres;
import dev.araga.daos.ExpensesDaoPostgres;
import dev.araga.entities.Employee;
import dev.araga.entities.Expenses;
import dev.araga.services.*;
import dev.araga.utils.JwtUtil;
import io.javalin.http.Handler;

import java.util.Set;

public class ExpensesController {

    private EmployeeService eserv = new EmployeeServiceImpl(new EmployeeDaoPostgres());
    private ExpenseService exserv = new ExpenseServiceImpl(new ExpensesDaoPostgres());

    public Handler getAllExpensesHandler = ctx ->{
        int id2 = Integer.parseInt(ctx.pathParam("id2"));
        Set<Expenses> exAll = this.exserv.getExpensesByEmployeeId(id2);
        Gson gson = new Gson();
        String expenseJson = gson.toJson(exAll);
        ctx.result(expenseJson);
        ctx.status(200);
    };
    public Handler getEveryExpensesHandler = ctx ->{
        Set<Expenses> exAll = this.exserv.getAllExpenses();
        Gson gson = new Gson();
        String expenseJson = gson.toJson(exAll);
        ctx.result(expenseJson);
        ctx.status(200);
    };



    public Handler getExpensesByIdHandler = (ctx) -> {
        int id2 = Integer.parseInt(ctx.pathParam("id2"));
        int id3 = Integer.parseInt(ctx.pathParam("id3"));
        Employee emp = this.eserv.getEmployeeById(id2);
        Expenses ex = this.exserv.getExpensesById(id3);
        if((emp == null) || (ex == null)){
            ctx.result("Expense or employee was not found");
            ctx.status(404);
        }else if (ex.getEmployeeId() == emp.getEmployeeId()){
            Gson gson = new Gson();
            String expenseJson = gson.toJson(ex);
            ctx.result(expenseJson);
            ctx.status(200);
        } else {
            ctx.result("Expense does not exist");
        }
    };

    public Handler createExpensesHandler = (ctx) ->{
        try{
            String body = ctx.body();
            Gson gson = new Gson();
            Expenses e = gson.fromJson(body, Expenses.class);
            this.exserv.createExpense(e);
            String json = gson.toJson(e);
            ctx.result(json);
            ctx.status(201);
        }catch(JsonSyntaxException jsonSyntaxException){
            throw(jsonSyntaxException);
        }

    };


    public Handler updateExpensesByIdHandler = (ctx) ->{
        int id2 = Integer.parseInt(ctx.pathParam("id2"));
        int id3 = Integer.parseInt(ctx.pathParam("id3"));
        Employee e = this.eserv.getEmployeeById(id2);
        Expenses ex = this.exserv.getExpensesById(id3);

        if(e == null || ex == null){
            ctx.result("Expense or employee was not found");
            ctx.status(404);
        }else if (e.getEmployeeId() == ex.getEmployeeId()){
            String body = ctx.body();
            Gson gson = new Gson();
            Expenses exp = gson.fromJson(body, Expenses.class);
            exp.setExpenseId(id3);
            this.exserv.updateExpenses(exp);
            ctx.result("good job");
        }else {
            ctx.result("Expense does not exist");
        }
    };

    public Handler deleteEmployeeHandler = (ctx) ->{
        int id2 = Integer.parseInt(ctx.pathParam("id2"));
        int id3 = Integer.parseInt(ctx.pathParam("id3"));
        Employee e = this.eserv.getEmployeeById(id2);
        Expenses ex = this.exserv.getExpensesById(id3);
        try{
            String jwt = ctx.header("Authorization");
            DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt); // returns decodeJWT or throws an exception
            if(decodedJWT.getClaim("role").asString().equals("Manager")){
                boolean deleted = this.eserv.deleteEmployeeById(id3);;
                if(deleted){
                    ctx.result("Book was deleted");
                }else{
                    ctx.result("Oh no could not delete");
                }
            }

        }catch (Exception exception){
            exception.printStackTrace();
            ctx.status(403);
            ctx.result("Missing authorization or improper token");
        }
//        if(e == null || ex == null){
//            ctx.result("Expense or employee was not found");
//            ctx.status(404);
//            //ctx.result("Could not delete");
//        }else if (e.getEmployeeId() == ex.getEmployeeId()){
//            this.eserv.deleteEmployeeById(id3);
//            ctx.result("Employee was deleted");
//        }else {
//            ctx.result("Manager does not manage that particular employee");
//        }
    };



}
