package dev.araga.servicetests;


import dev.araga.daos.ExpensesDaoPostgres;
import dev.araga.entities.Expenses;
import dev.araga.services.ExpenseService;
import dev.araga.services.ExpenseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ExpensesServiceMockitoTests {

    @Mock
    ExpensesDaoPostgres expensesDaoPostgres = null;

    private ExpenseService expenseService = null;

    @BeforeEach
    void setUp(){
        //Fake Expenses
        Expenses f1 = new Expenses(1,100,"Air Ticket","Pending","",(System.currentTimeMillis()/1000),((System.currentTimeMillis()/1000)+64000),1);
        Expenses f2 = new Expenses(2,100,"Air Ticket","Pending","",(System.currentTimeMillis()/1000),((System.currentTimeMillis()/1000)+64000),1);
        Expenses f3 = new Expenses(3,100,"Air Ticket","Approved","",(System.currentTimeMillis()/1000),((System.currentTimeMillis()/1000)+64000),2);
        Expenses f4 = new Expenses(4,100,"Air Ticket","Approved","",(System.currentTimeMillis()/1000),((System.currentTimeMillis()/1000)+64000),2);
        Expenses f5 = new Expenses(5,100,"Air Ticket","Denied","",(System.currentTimeMillis()/1000),((System.currentTimeMillis()/1000)+64000),3);
        Set<Expenses> expensesSet = new HashSet<Expenses>();

        expensesSet.add(f1);
        expensesSet.add(f2);
        expensesSet.add(f3);
        expensesSet.add(f4);
        expensesSet.add(f5);

        Mockito.when(expensesDaoPostgres.getAllExpenses()).thenReturn(expensesSet);
        this.expenseService = new ExpenseServiceImpl(this.expensesDaoPostgres);

    }

    @Test
    void get_all_expense_by_employee_id(){
        Set<Expenses> expenses = this.expenseService.getExpensesByEmployeeId(1);
        System.out.println(expenses);
        Assertions.assertEquals(2, expenses.size());
    }

    @Test
    void get_all_approved_expenses(){
        Set<Expenses> expenses = this.expenseService.getAllApprovedExpenses("Pending");
        System.out.println(expenses);
        Assertions.assertEquals(2, expenses.size());
    }



}
