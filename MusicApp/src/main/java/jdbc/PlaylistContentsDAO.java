package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PlaylistContentsDAO extends Database{
	public void addPlaylistContents (PlaylistContents playlistContents) throws SQLException{
		try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement(
	            		 "INSERT INTO PlaylistContents (PlaylistId, AudioId) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, playlistContents.getPlaylistId());
			stmt.setInt(2, playlistContents.getAudioId());
			int affectedRows = stmt.executeUpdate();
			
			if (affectedRows > 0) {
				try (ResultSet generatedKeys = stmt.getGeneratedKeys()){
					if(generatedKeys.next()) {
						playlistContents.setPlaylistContentsId(generatedKeys.getInt(1));
					}
				}
			}
		}
	}
	
	public void updatePlaylistContents(PlaylistContents playlistContents) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE PlaylistContents SET AudioId = ? WHERE PlaylistContentsId = ?")) {
            stmt.setInt(1, playlistContents.getAudioId());
            stmt.setInt(2, playlistContents.getPlaylistContentsId()); 
            stmt.executeUpdate();
        }
    }

    // Delete an playlist content
    public void deletePlaylistContents(int PlaylistContentsId) throws SQLException {
        String query = "DELETE FROM PlaylistContents WHERE PlaylistContentsID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, PlaylistContentsId); // Use PlaylistContentsID to delete a specific order detail
            stmt.executeUpdate();
        }
    }

    // Retrieve order details by OrderID
    public List<PlaylistContents> getPlaylistContentsByOrderId(int orderID) throws SQLException {
        List<PlaylistContents> playlistContentsList = new ArrayList<>();
        String query = "SELECT * FROM PlaylistContents WHERE OrderID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                playlistContentsList.add(new PlaylistContents(
                        rs.getInt("PlaylistContentsId"),  // Retrieve the generated PlaylistContentsID
                        rs.getInt("PlaylistId"),
                        rs.getInt("AudioId")
                ));
            }
        }
        return playlistContentsList;
    }

    // Retrieve all order details
    public List<PlaylistContents> getAllPlaylistContents() throws SQLException {
        List<PlaylistContents> playlistContentsList = new ArrayList<>();
        String query = "SELECT * FROM PlaylistContents";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                playlistContentsList.add(new PlaylistContents(
                        rs.getInt("PlaylistContentsId"),  // Retrieve the generated PlaylistContentsID
                        rs.getInt("PlaylistId"),
                        rs.getInt("AudioId")
                ));
            }
        }
        return playlistContentsList;
    }

    public void deleteAllPlaylistContents() throws SQLException {
        String query = "DELETE FROM PlaylistContents";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println("Deleted " + rowsAffected + " order details.");
        }
    }
    
}