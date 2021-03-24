package dev.araga.servicetests;


import dev.araga.daos.ExpensesDaoPostgres;
import dev.araga.entities.Expenses;
import dev.araga.services.ExpenseService;
import dev.araga.services.ExpenseServiceImpl;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseServiceTest {

    private static ExpenseService eserv = new ExpenseServiceImpl(new ExpensesDaoPostgres());
    private static Expenses testExpenses = null;


    @Test
    @Order(1)
    void create_expenses(){
        Expenses firstExpense = new Expenses(0,300, "Traveling", "Pending","",(System.currentTimeMillis()/1000),((System.currentTimeMillis()/1000)+64000),1);
        eserv.createExpense(firstExpense);
        System.out.println(firstExpense);
        testExpenses = firstExpense;
        System.out.println(testExpenses.getExpenseId());
        Assertions.assertNotEquals(0, firstExpense.getExpenseId());

    }



}
