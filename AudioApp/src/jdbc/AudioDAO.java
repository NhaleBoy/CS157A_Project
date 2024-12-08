package jdbc;

import jdbc.Audio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AudioDAO extends Database{
	
	
	public void addAudio(Audio audio) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
            		 "INSERT INTO Audios (Title, AuthorId, Category, Genre, Length) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, audio.getTitle());
            stmt.setInt(2, audio.getAuthorId());
            stmt.setString(3, audio.getCategory());
            stmt.setString(4, audio.getGenre());
            stmt.setFloat(5, audio.getLength());
            stmt.executeUpdate();
        }
        
	}
        
        
        public void updateAudio(Audio audio) throws SQLException {
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                		 "UPDATE Customers SET Title = ?, AuthorId = ?, Category = ?, Genre = ? , Length = ? WHERE UserID = ?")) {
            	stmt.setString(1, audio.getTitle());
                stmt.setInt(2, audio.getAuthorId());
                stmt.setString(3, audio.getCategory());
                stmt.setString(4, audio.getGenre());
                stmt.setFloat(5, audio.getLength());
                stmt.setInt(5, audio.getAudioID());
                stmt.executeUpdate();
            }
        }
        
        
        public void deleteAudio(int audioId) throws SQLException {
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                		 "DELETE FROM Audios WHERE AudioId = ?")) {
                stmt.setInt(1, audioId);
                stmt.executeUpdate();
            }
        }

        
        public Audio getAudioById(int audioId) throws SQLException {
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Audios WHERE AudioId = ?")) {
                stmt.setInt(1, audioId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Audio(
                            rs.getInt("AudioID"),
                            rs.getString("Title"),
                            rs.getInt("AuthorId"),
                            rs.getString("Category"),
                            rs.getString("Genre"),
                            rs.getFloat("length")
                            
                    );
                }
            }
            return null;
        }

        // Get all Audios
        public List<Audio> getAllAudios() throws SQLException {
            List<Audio> audios = new ArrayList<>();
            try (Connection conn = getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM Audios")) {
                while (rs.next()) {
                    audios.add(new Audio(
                    		rs.getInt("AudioID"),
                            rs.getString("Title"),
                            rs.getInt("AuthorId"),
                            rs.getString("Category"),
                            rs.getString("Genre"),
                            rs.getFloat("length")
                    ));
                }
            }
            return audios;
        }
        
        public boolean audioExists(String title) throws SQLException {
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Audios WHERE Title = ?")) {
                stmt.setString(1, title);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Return true if count > 0
                }
            }
            return false;
        }

      
        public void addAudiossIfNotExist(List<Audio> audios) throws SQLException {
            for (Audio audio : audios) {
                if (!audioExists(audio.getTitle())) {
                	addAudio(audio);
                }
            }
        }
        
  
        public void deleteAllAudios() throws SQLException {
            try (Connection conn = getConnection();
                 Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate("DELETE FROM Audios");
                System.out.println("Deleted " + rowsAffected + " audios.");
            }
        }
    }
}