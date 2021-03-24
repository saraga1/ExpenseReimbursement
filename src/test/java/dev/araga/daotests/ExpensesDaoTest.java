package dev.araga.daotests;

import dev.araga.daos.ExpensesDAO;
import dev.araga.daos.ExpensesDaoPostgres;
import dev.araga.entities.Expenses;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpensesDaoTest {

    private static ExpensesDAO edao = new ExpensesDaoPostgres();
    private static Expenses testExpense = null;

    @Test
    @Order(1)
    void create_expense(){
        Expenses firstExpense = new Expenses(0,300, "Traveling", "Pending","",(System.currentTimeMillis()/1000),((System.currentTimeMillis()/1000)+64000),1);
        edao.createExpenses(firstExpense);
        System.out.println(firstExpense);
        testExpense = firstExpense;
        System.out.println(testExpense.getExpenseId());
        Assertions.assertNotEquals(0, firstExpense.getExpenseId());
    }

    @Test
    @Order(2)
    void get_expense_by_id(){
        Expenses firstExpense = new Expenses(0,300, "Traveling", "Pending","",(System.currentTimeMillis()/1000),((System.currentTimeMillis()/1000)+64000),1);
        edao.createExpenses(firstExpense);
        System.out.println(firstExpense);
        testExpense = firstExpense;
        int id = testExpense.getExpenseId();
        System.out.println(id);

        Assertions.assertEquals(testExpense.getReason(), firstExpense.getReason());

    }

    @Test
    @Order(3)
    void update_expense(){
       // int id = 73;//testExpense.getExpenseId();
        Expenses expense = edao.getExpensesById(testExpense.getExpenseId());
        expense.setAmount(1000);
        System.out.println(expense.getAmount());
        edao.updateExpenses(expense);
        System.out.println(edao);

        Expenses updateddExpenses = edao.getExpensesById(testExpense.getExpenseId());
        System.out.println(updateddExpenses.getAmount());
        Assertions.assertEquals(1000, updateddExpenses.getAmount());
        //System.out.println(id+"kkkjhhjh");

//        Expenses firstExpense = new Expenses(0,300, "Traveling", "Pending",(System.currentTimeMillis()/1000),((System.currentTimeMillis()/1000)+64000),1);
//        edao.createExpenses(firstExpense);
//        //Expenses expenses = edao.getExpensesById(firstExpense.getExpenseId());
//        firstExpense.setReason("Just for fun");
//        edao.updateExpenses(firstExpense);
//        System.out.println(edao.getExpensesById(firstExpense.getExpenseId()).getReason() + "kj");
//       // Expenses updateExpense = edao.getExpensesById(firstExpense.getExpenseId()).getReason();
//       // Assertions.assertEquals("Just for fun", edao.getExpensesById(firstExpense.getExpenseId()).getReason());
    }


    @Test
    @Order(4)
    void get_all_books(){
        Expenses e1 = new Expenses(0,400, "Traveling", "Pending","",(System.currentTimeMillis()/1000),((System.currentTimeMillis()/1000)+64000),1);
        Expenses e2 = new Expenses(0,500, "Lodging", "Pending","",(System.currentTimeMillis()/1000),((System.currentTimeMillis()/1000)+64000),1);
        Expenses e3 = new Expenses(0,600, "Air ticket", "Pending","",(System.currentTimeMillis()/1000),((System.currentTimeMillis()/1000)+64000),1);
        Set<Expenses> allExpenses = edao.getAllExpenses();
        allExpenses.add(edao.createExpenses(e1));
        allExpenses.add(edao.createExpenses(e2));
        allExpenses.add(edao.createExpenses(e3));

        Assertions.assertTrue(allExpenses.size()>2);
        System.out.println(allExpenses.size()+"hkkhlkjhlkhl");

    }

    @Test
    @Order(5)
    void delete_expenses_by_id(){
        int id = testExpense.getExpenseId();
        boolean result = edao.deleteExpensesById(id);
        Assertions.assertTrue(result);
    }



}
