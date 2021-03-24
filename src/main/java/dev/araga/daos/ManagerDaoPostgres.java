package dev.araga.daos;

import dev.araga.entities.Manager;
import dev.araga.utils.ConnectionUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ManagerDaoPostgres implements ManagerDAO{

    private Logger logger = Logger.getLogger(ManagerDaoPostgres.class.getName());



    @Override
    public Manager createManager(Manager manager) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into manager (fname,lname,username,m_password) values (?,?,?,?)"; // Anything
            // that requires you to write precise string is immediately very likely to cause bugs
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // when you create something
            ps.setString(1, manager.getFname());// for the first ? put the title
            ps.setString(2,manager.getLname());
            ps.setString(3,manager.getUsername());
            ps.setString(4,manager.getPassword());

            ps.execute(); // execute the sql statement

            // result set is a cursor that starts before the actual first element
            ResultSet rs = ps.getGeneratedKeys(); // return the value of the generated key // the id
            rs.next(); // the first element
            int key = rs.getInt("manager_id");
            manager.setManagerId(key);
            return  manager;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to create manager",sqlException);
            return null;
        }
    }

    @Override
    public Set<Manager> getAllManagers() {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from manager";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Set<Manager> managers = new HashSet<Manager>();
            // while there exists books create a new book and store the infromation into the new book
            while (rs.next()){
                Manager manager = new Manager();
                manager.setManagerId(rs.getInt("manager_id"));
                manager.setFname(rs.getString("fname"));
                manager.setLname(rs.getString("lname"));
                manager.setUsername(rs.getString("username"));
                manager.setPassword(rs.getString("m_password"));
                managers.add(manager);
            }
            return  managers;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to get all books",sqlException);
            return null;
        }
    }

    @Override
    public Manager getManagerById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from manager where manager_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();

                Manager manager = new Manager();
                manager.setManagerId(rs.getInt("manager_id"));
                manager.setFname(rs.getString("fname"));
                manager.setLname(rs.getString("lname"));
                manager.setUsername(rs.getString("username"));
                manager.setPassword(rs.getString("m_password"));

            return  manager;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to get manager by id",sqlException);
            return null;
        }
    }

    @Override
    public Manager updateManager(Manager manager) {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update manager set fname = ?, lname = ?,username = ?,m_password = ? where manager_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql); // when you create something
            ps.setString(1, manager.getFname());// for the first ? put the title
            ps.setString(2,manager.getLname());
            ps.setString(3,manager.getUsername());
            ps.setString(4,manager.getPassword());
            ps.setInt(5,manager.getManagerId());
            ps.executeUpdate(); // execute the sql statement

//            // result set is a cursor that starts before the actual first element
//            ResultSet rs = ps.getGeneratedKeys(); // return the value of the generated key // the id
//            rs.next(); // the first element
//            int key = rs.getInt("manager_id");
//            manager.setManagerId(key);
            return  manager;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to create manager",sqlException);
            return null;
        }
    }

    @Override
    public boolean deleteManagerById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from manager where manager_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            return true;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to delete manager with id " + id,sqlException);
            return false;
        }
    }
}
