package dev.araga.app;

import dev.araga.controllers.EmployeeController;
import dev.araga.controllers.ExpensesController;
import dev.araga.controllers.LoginController;
import dev.araga.controllers.ManagerController;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args){


        ManagerController managerController = new ManagerController();
        EmployeeController employeeController = new EmployeeController();
        ExpensesController expensesController = new ExpensesController();
        LoginController loginController = new LoginController();


        Javalin app = Javalin.create(
                config -> {
                    config.enableCorsForAllOrigins();//allows the server to process JS anywhere
                }
        );

       app.get("/manager",managerController.getAllManagerHandler);
       app.get("/manager/:id",managerController.getManagerByIdHandler);
       app.post("/manager",managerController.createManagerHandler);
       app.put("/manager/:id",managerController.updateManagerHandler);
       app.delete("/manager/:id",managerController.deleteManagerHandler);

       app.get("/manager/:id/employee",employeeController.getAllEmployeeHandler);
       app.post("/manager/:id/employee",employeeController.createEmployeeHandler);
       app.get("/manager/:id/employee/:id2", employeeController.getEmployeeByIdHandler);
       app.put("/manager/:id/employee/:id2", employeeController.updateEmployeeHandler);
       app.delete("/manager/:id/employee/:id2", employeeController.deleteEmployeeHandler);

       app.get("/manager/id/employee/id2/expenses", expensesController.getEveryExpensesHandler);
       app.get("/manager/:id/employee/:id2/expenses", expensesController.getAllExpensesHandler);
       app.post("/manager/:id/employee/:id2/expenses", expensesController.createExpensesHandler);
       app.get("/manager/:id/employee/:id2/expenses/:id3", expensesController.getExpensesByIdHandler);
       app.put("/manager/:id/employee/:id2/expenses/:id3", expensesController.updateExpensesByIdHandler);
       app.delete("/manager/:id/employee/:id2/expenses/:id3", expensesController.deleteEmployeeHandler);

        app.post("/users/login/manager",loginController.loginHandler);
        app.post("/users/login/employee", loginController.loginEmpHandler);


       app.start(); // actually starts the web server
    }

}
