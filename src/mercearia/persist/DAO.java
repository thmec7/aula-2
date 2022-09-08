package mercearia.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    public DAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            System.err.println("Class not found. Error: " + cnfe.getMessage());
        }
    }
    protected Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost/mercearia", "root","");

    }









}
