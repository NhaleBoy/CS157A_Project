package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class create_schema extends Database {
public static void main(String[] args) {
    	
        String dropTablesSQL1 = "DROP TABLE IF EXISTS Users;";
        String dropTablesSQL2 = "DROP TABLE IF EXISTS Playlists;";
        String dropTablesSQL3 = "DROP TABLE IF EXISTS Audios;";
        String dropTablesSQL4 = "DROP TABLE IF EXISTS PlaylistContains;";
        
        // SQL queries to create tables

        String createTablesSQL1 = "CREATE TABLE IF NOT EXISTS Users (" +
                "UserId INT AUTO_INCREMENT PRIMARY KEY, " +
                "Username VARCHAR(100) NOT NULL UNIQUE, " +  // for username
                "PrefGenre VARCHAR(100), " +   /// should start null?
                "Email VARCHAR(255) NOT NULL, " +
                "Password VARCHAR(255) NOT NULL" +  //for user password
                ");";

        String createTablesSQL2 = "CREATE TABLE IF NOT EXISTS Playlists (" +
                "PlaylistId INT AUTO_INCREMENT PRIMARY KEY, " +
                "AuthorId INT NOT NULL, " +
                "Title VARCHAR(100) NOT NULL, " +
                "FOREIGN KEY (AuthorId) REFERENCES Users(UserId), " +
                ");";

        String createTablesSQL3 = "CREATE TABLE IF NOT EXISTS Audios (" +
                "AudioId INT AUTO_INCREMENT PRIMARY KEY, " +
                "Title VARCHAR(100) NOT NULL, " +
                "Genre VARCHAR(50) NOT NULL" +
                "Category VARCHAR(50) NOT NULL" +
                "FilePath VARCHAR(100) NOT NULL, " +
                "AuthorId INT NOT NULL, " +
                "FOREIGN KEY (AuthorId) REFERENCES Users(UserId), " +
                ");";

        String createTablesSQL4 = "CREATE TABLE IF NOT EXISTS PlaylistContents (" +
                "PlaylistContentsId INT AUTO_INCREMENT PRIMARY KEY, " +
                "PlaylistId INT NOT NULL, " +
                "AudioId INT NOT NULL, " +
                "FOREIGN KEY (PlaylistId) REFERENCES Playlist(PlaylistId), " +
                "FOREIGN KEY (AudioId) REFERENCES Audio(AudioId)" +
                ");";

        // Create connection to the database
        try {
            // Optional: Load MySQL JDBC driver (not required for JDBC 4.0 and above)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection to the database
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                System.out.println("Connection successful!");

                // Create a Statement to execute SQL queries
                try (Statement stmt = conn.createStatement()) {
                	
                	stmt.executeUpdate(dropTablesSQL1);
                    stmt.executeUpdate(dropTablesSQL2);
                    stmt.executeUpdate(dropTablesSQL3);
                    stmt.executeUpdate(dropTablesSQL4);

                    System.out.println("Old tables deleted (if they existed).");
                	
                    // Execute each SQL command to create tables if they do not exist
                    stmt.executeUpdate(createTablesSQL1);
                    stmt.executeUpdate(createTablesSQL2);
                    stmt.executeUpdate(createTablesSQL3);
                    stmt.executeUpdate(createTablesSQL4);

                    System.out.println("Tables created or already exist.");
                } catch (SQLException e) {
                    System.out.println("Error executing SQL commands.");
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Connection failed!");
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found!");
            e.printStackTrace();
        }
    }
}

