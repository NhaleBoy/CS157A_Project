package jdbc;

import jdbc.Audio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AudioDAO extends Database{
	
	
	public void addAudio(Audio audio) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
            		 "INSERT INTO Audios (Title, AuthorId, Category, Genre, FilePath) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, audio.getTitle());
            stmt.setInt(2, audio.getAuthorId());
            stmt.setString(3, audio.getCategory());
            stmt.setString(4, audio.getGenre());
            stmt.setString(5, audio.getFilePath());
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
                stmt.setString(5, audio.getFilePath());
                stmt.setInt(5, audio.getAudioID());
                stmt.execute();
            }
        }
        
        
        public void deleteAudio(int audioId) throws SQLException {
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                		 "DELETE FROM Audios WHERE AudioId = ?")) {
                stmt.setInt(1, audioId);
                stmt.execute();
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
                            rs.getString("FilePath")
                            
                    );
                }
            }
            return null;
        }
        
        public int getAudioIdByTitle(String title)throws SQLException {
        	try (Connection conn = getConnection();
                    PreparedStatement stmt = conn.prepareStatement(
                   		 "SELECT AudioId FROM Audios WHERE Title = ?")) {
                   stmt.setString(1, title);
                   ResultSet rs = stmt.executeQuery();
                   if (rs.next()) {
                       return rs.getInt("AudioId");  
                   }
               }
        	return -1; // if not exist
        }
        
        public Audio getAudioThatIsSong(String title)throws SQLException {
        	try (Connection conn = getConnection();
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Audios WHERE Title = ? AND Category ='Song' ")) {
                   stmt.setString(1, title);
                   ResultSet rs = stmt.executeQuery();
                   if (rs.next()) {
                       return new Audio(
                               rs.getInt("AudioID"),
                               rs.getString("Title"),
                               rs.getInt("AuthorId"),
                               rs.getString("Category"),
                               rs.getString("Genre"),
                               rs.getString("FilePath")
                               
                       );
                   }
               }
               return null;
        }
        
        public Audio getAudioThatIsPod(String title)throws SQLException {
        	try (Connection conn = getConnection();
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Audios WHERE Title = ? AND Category ='Podcast' ")) {
                   stmt.setString(1, title);
                   ResultSet rs = stmt.executeQuery();
                   if (rs.next()) {
                       return new Audio(
                               rs.getInt("AudioID"),
                               rs.getString("Title"),
                               rs.getInt("AuthorId"),
                               rs.getString("Category"),
                               rs.getString("Genre"),
                               rs.getString("FilePath")
                               
                       );
                   }
               }
               return null;
        }
        
        public List<Audio> getAudiosByAuthor(int authorId) throws SQLException {
        	List<Audio> audios = new ArrayList<>();
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Audios WHERE AuthorId = ?")) {
                stmt.setInt(1, authorId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    audios.add(new Audio(
                            rs.getInt("AudioID"),
                            rs.getString("Title"),
                            rs.getInt("AuthorId"),
                            rs.getString("Category"),
                            rs.getString("Genre"),
                            rs.getString("FilePath")
                            
                    ));
                }
            }
            return audios;
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
                            rs.getString("FilePath")
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
