package jdbc;

import jdbc.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends Database{
	
	public void addUser (User user) throws SQLException {
		// check if username already exists
		try (Connection conn = getConnection();
		         PreparedStatement checkStmt = conn.prepareStatement(
		        		 "SELECT COUNT(*) FROM Users WHERE Username = ?")) {
			
			checkStmt.setString(1, user.getUsername());
	        ResultSet rs = checkStmt.executeQuery();
	        rs.next();
	        int count = rs.getInt(1);
	        
	        if (count > 0) {
	            throw new SQLException("A User with the Username: '" + user.getUsername() + "' already exists.");
	        }
		}
		// username is valid, create new user
		try (Connection conn = getConnection();
		         PreparedStatement stmt = conn.prepareStatement(
		        		 "INSERT INTO Users (Username, Password, Email, PrefGenre) VALUES (?,?,?,?)")) {
		        
		        stmt.setString(1, user.getUsername());
		        stmt.setString(2, user.getPassword());
		        stmt.setString(3, user.getEmail());
		        stmt.setString(4, user.getPrefGenre());
		        stmt.executeUpdate();
		    }
	}
	
	
	
	public void updateUser(User user) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
            		 "UPDATE Customers SET Username = ?, Password = ?, Email = ?, PrefGenre = ? WHERE UserID = ?")) {
        	stmt.setString(1, user.getUsername());
	        stmt.setString(2, user.getPassword());
	        stmt.setString(3, user.getEmail());
	        stmt.setString(4, user.getPrefGenre()); 
            stmt.setInt(5, user.getUserId());
            stmt.executeUpdate();
        }
    }
	
	public void deleteUser(int userId) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
            		 "DELETE FROM Users WHERE UserID = ?")) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }
	
	public int getUserIdViaUsername(String username) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
            		 "SELECT UserID FROM Users WHERE Username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("UserID");  
            }
        }
        return -1;  // Return -1 if no customer with username is found
    }
	
	
	public User getUserViaId(int userId) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
            		 "SELECT * FROM Users WHERE UserID = ?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("PrefGenre")
                );
            }
        }
        return null;
    }
	
	// get all users in one list
	public List<User> getUserbase() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
            		"SELECT * FROM Users")) {
            while (rs.next()) {
                users.add(new User(
                		rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("PrefGenre")
                ));
            }
        }
        return users;
    }
	
	public boolean checkLogin(String username, String password) throws SQLException {
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
            		"SELECT * FROM Users WHERE Username = ? AND Password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);  
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // If a row is returned, vaild login
        }
    }
	
	
	public boolean doesUsernameExist(String username) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
            		 "SELECT 1 FROM Users WHERE Username = ? LIMIT 1")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();  // Returns true if a row is found
            }
        }
    }
    

    public void deleteAllUserss() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate("DELETE FROM Users");
            System.out.println("Deleted " + rowsAffected + " users.");
        }
    }
}
