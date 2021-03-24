package dev.araga.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection(){

        //String details = System.getenv("CONN_DETAILS");
        String details ="jdbc:postgresql://35.237.39.104:5432/expenseReimbursement?user=Samuel&password=quest456";

        try{
            Connection conn = DriverManager.getConnection(details);
            return conn;

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }
}
