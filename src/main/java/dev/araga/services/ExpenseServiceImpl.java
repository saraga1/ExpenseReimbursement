package dev.araga.services;

import dev.araga.daos.ExpensesDaoPostgres;
import dev.araga.entities.Expenses;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

public class ExpenseServiceImpl implements ExpenseService{

    private ExpensesDaoPostgres exdao;

    public ExpenseServiceImpl(ExpensesDaoPostgres expensesDaoPostgres){
        this.exdao = expensesDaoPostgres;
    }

    @Override
    public Expenses createExpense(Expenses expense) {
        //String stat = expense.getStatus();
        //expense.setStatus("Pending");

        expense.setDateSubmitted(System.currentTimeMillis());
        return this.exdao.createExpenses(expense);
    }

    @Override
    public Set<Expenses> getAllExpenses() {
        return this.exdao.getAllExpenses();
    }

    @Override
    public Set<Expenses> getExpensesByEmployeeId(int id) {
        Set<Expenses> allExpenses = this.exdao.getAllExpenses();
        Set<Expenses> selectedExpenses = new HashSet<Expenses>();

        try{
            for(Expenses ex : allExpenses){
                if(ex.getEmployeeId() == id) {
                    selectedExpenses.add(ex);
                }
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return selectedExpenses;
    }

    @Override
    public Set<Expenses> getAllApprovedExpenses(String result) {
        Set<Expenses> allExpenses = this.exdao.getAllExpenses();
        Set<Expenses> selectedExpenses = new HashSet<Expenses>();
        try{
            for(Expenses ex : allExpenses){
                if(ex.getStatus().equalsIgnoreCase(result)) {
                    selectedExpenses.add(ex);
                }
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return selectedExpenses;
    }

    @Override
    public Expenses getExpensesById(int id) {
        return this.exdao.getExpensesById(id);
    }

    @Override
    public Expenses updateExpenses(Expenses expense) {
        return this.exdao.updateExpenses(expense);
    }

    @Override
    public boolean deleteExpeneseById(int id) {
        return this.exdao.deleteExpensesById(id);
    }
}
