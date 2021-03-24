package dev.araga.services;

import dev.araga.entities.Expenses;

import java.util.Set;

public interface ExpenseService {

    Expenses createExpense(Expenses expense);

    Set<Expenses> getAllExpenses();
    Set<Expenses> getExpensesByEmployeeId(int id);
    Set<Expenses> getAllApprovedExpenses(String result);
    Expenses getExpensesById(int id);

    Expenses updateExpenses(Expenses expense);

    boolean deleteExpeneseById(int id);



}
