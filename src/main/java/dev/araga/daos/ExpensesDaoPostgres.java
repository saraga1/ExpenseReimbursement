package dev.araga.daos;

import dev.araga.entities.Expenses;
import dev.araga.utils.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;



public class ExpensesDaoPostgres implements ExpensesDAO{

    private Logger logger = Logger.getLogger(ExpensesDaoPostgres.class.getName());

    @Override
    public Expenses createExpenses(Expenses expenses) {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into expenses (amount, reason, status, manager_input, date_submitted,date_result, e_id) values(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,expenses.getAmount());
            ps.setString(2,expenses.getReason());
            ps.setString(3,expenses.getStatus());
            ps.setString(4, expenses.getManagerInput());
            ps.setLong(5,expenses.getDateSubmitted());
            ps.setLong(6,expenses.getDateOfResult());
            ps.setInt(7,expenses.getEmployeeId());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt("expenses_id");
            expenses.setExpenseId(key);
            return expenses;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("Unable to create expenses",sqlException);
            return null;

        }

    }

    @Override
    public Set<Expenses> getAllExpenses() {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from expenses";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Set<Expenses> expenses = new HashSet<Expenses>();

            while (rs.next()) {
                Expenses expense = new Expenses();
                expense.setExpenseId(rs.getInt("expenses_id"));
                expense.setAmount(rs.getInt("amount"));
                expense.setReason(rs.getString("reason"));
                expense.setStatus(rs.getString("status"));
                expense.setManagerInput(rs.getString("manager_input"));
                expense.setDateSubmitted(rs.getLong("date_submitted"));
                expense.setDateOfResult(rs.getLong("date_result"));
                expense.setEmployeeId(rs.getInt("e_id"));
                expenses.add(expense);

            }

            return expenses;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("Unable to create expenses",sqlException);
            return null;

        }

    }

    @Override
    public Expenses getExpensesById(int id) {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from expenses where expenses_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();


                Expenses expense = new Expenses();
                expense.setExpenseId(rs.getInt("expenses_id"));
                expense.setAmount(rs.getInt("amount"));
                expense.setReason(rs.getString("reason"));
                expense.setStatus(rs.getString("status"));
                expense.setManagerInput(rs.getString("manager_input"));
                expense.setDateSubmitted(rs.getLong("date_submitted"));
                expense.setDateOfResult(rs.getLong("date_result"));
                expense.setEmployeeId(rs.getInt("e_id"));


            return expense;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("Unable to get expenses by Id",sqlException);
            return null;

        }
    }

    @Override
    public Expenses updateExpenses(Expenses expenses) {
        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "update expenses set amount = ?, reason = ?, status = ?, manager_input = ?, date_submitted = ?, date_result = ?, e_id = ? where expenses_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,expenses.getAmount());
            ps.setString(2,expenses.getReason());
            ps.setString(3,expenses.getStatus());
            ps.setString(4, expenses.getManagerInput());
            ps.setLong(5,expenses.getDateSubmitted());
            ps.setLong(6,expenses.getDateOfResult());
            ps.setInt(7,expenses.getEmployeeId());
            ps.setInt(8, expenses.getExpenseId());
            ps.executeUpdate();
            //System.out.println(ps.executeUpdate());
            return expenses;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("Unable to update expenses");
            return null;

        }
    }

    @Override
    public boolean deleteExpensesById(int id) {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from expenses where expenses_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            return true;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to delete book with id " + id,sqlException);
            return false;
        }
    }
}
