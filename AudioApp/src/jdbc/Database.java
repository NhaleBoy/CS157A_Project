package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Driver;
import java.util.Enumeration;

public class Database {
	// we prolly wont use a main
	protected static final String URL = "jdbc:mysql://localhost:3306/Demo";
    protected static final String USER = "root";
    protected static final String PASSWORD = ""; //unsure
	
    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }

        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
           //.out.println("Registered driver: " + driver.getClass().getName());
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
