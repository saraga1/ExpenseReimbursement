package dev.araga.daos;


import dev.araga.entities.Expenses;

import java.util.Set;

public interface ExpensesDAO {

    //CREATE
    Expenses createExpenses(Expenses expenses);

    //READ
    Set<Expenses> getAllExpenses();
    Expenses getExpensesById(int id);

    //UPDATE
    Expenses updateExpenses(Expenses expenses);

    //DELETE
    boolean deleteExpensesById(int id);


}
