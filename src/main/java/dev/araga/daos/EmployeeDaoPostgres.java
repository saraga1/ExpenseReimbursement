package dev.araga.daos;

import dev.araga.entities.Employee;
import dev.araga.entities.Manager;
import dev.araga.utils.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class EmployeeDaoPostgres implements EmployeeDAO {

    private Logger logger = Logger.getLogger(EmployeeDaoPostgres.class.getName());


    @Override
    public Employee createEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into employee (fname,lname,username,e_password,m_id) values (?,?,?,?,?)"; // Anything
            // that requires you to write precise string is immediately very likely to cause bugs
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // when you create something
            ps.setString(1, employee.getFname());// for the first ? put the title
            ps.setString(2,employee.getLname());
            ps.setString(3,employee.getUsername());
            ps.setString(4,employee.getPassword());
            ps.setInt(5,employee.getM_id());

            ps.execute(); // execute the sql statement

            // result set is a cursor that starts before the actual first element
            ResultSet rs = ps.getGeneratedKeys(); // return the value of the generated key // the id
            rs.next(); // the first element
            int key = rs.getInt("employee_id");
            employee.setEmployeeId(key);
            return  employee;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            //logger.error("unable to create manager",sqlException);
            return null;
        }
    }

    @Override
    public Set<Employee> getAllEmployee() {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from employee";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Set<Employee> employees = new HashSet<Employee>();
            // while there exists books create a new book and store the infromation into the new book
            while (rs.next()){
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFname(rs.getString("fname"));
                employee.setLname(rs.getString("lname"));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("e_password"));
                employee.setM_id(rs.getInt("m_id"));
                employees.add(employee);
            }
            return  employees;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            //logger.error("unable to get all books",sqlException);
            return null;
        }


    }

    @Override
    public Employee getEmployeeById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from employee where employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Employee e1 = new Employee();
            e1.setEmployeeId(rs.getInt("employee_id"));
            e1.setFname(rs.getString("fname"));
            e1.setLname(rs.getString("lname"));
            e1.setUsername(rs.getString("username"));
            e1.setPassword(rs.getString("e_password"));
            e1.setM_id(rs.getInt("m_id"));

            return  e1;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            //logger.error("unable to get manager by id",sqlException);
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update employee set fname = ?, lname = ?,username = ?,e_password = ?, m_id = ? where employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql); // when you create something
            ps.setString(1, employee.getFname());// for the first ? put the title
            ps.setString(2,employee.getLname());
            ps.setString(3,employee.getUsername());
            ps.setString(4,employee.getPassword());
            ps.setInt(5, employee.getM_id());
            ps.setInt(6,employee.getEmployeeId());
            ps.executeUpdate(); // execute the sql statement

//            // result set is a cursor that starts before the actual first element
//            ResultSet rs = ps.getGeneratedKeys(); // return the value of the generated key // the id
//            rs.next(); // the first element
//            int key = rs.getInt("manager_id");
//            manager.setManagerId(key);
            return  employee;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
           // logger.error("unable to create manager",sqlException);
            return null;
        }

    }

    @Override
    public boolean deleteEmployeeById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from employee where employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            return true;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
           // logger.error("unable to delete manager with id " + id,sqlException);
            return false;
        }
    }
}
