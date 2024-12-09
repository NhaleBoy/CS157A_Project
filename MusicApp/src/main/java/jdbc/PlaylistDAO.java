package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO extends Database{
	// Insert a new playlist along with playlist contents into the database
    public void addPlaylist(Playlist playlist, List<PlaylistContents> playlistContents) throws SQLException {
        String insertPlaylistQuery = "INSERT INTO Playlists (authorId, title) VALUES (?, ?)";
        String insertPlaylistContentQuery = "INSERT INTO PlaylistContents (PlaylistId, AudioId) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement insertPlaylistStmt = conn.prepareStatement(insertPlaylistQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertPlaylistContentStmt = conn.prepareStatement(insertPlaylistContentQuery)) {

            // Insert into Playlists table
            insertPlaylistStmt.setInt(1, playlist.getAuthorId());
            insertPlaylistStmt.setString(2, playlist.getTitle());
            insertPlaylistStmt.executeUpdate();

            // Retrieve the generated PlaylistID
            int playlistId;
            try (ResultSet generatedKeys = insertPlaylistStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    playlistId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve PlaylistID.");
                }
            }

            // Insert playlist contents into PlaylistContents table
            for (PlaylistContents playlistContent : playlistContents) {
                insertPlaylistContentStmt.setInt(1, playlistId);
                insertPlaylistContentStmt.setInt(2, playlistContent.getAudioId());
                insertPlaylistContentStmt.executeUpdate();
            }
        }
    }
    
    public int addPlaylist(Playlist playlist) throws SQLException {
        String query = "INSERT INTO Playlists (AuthorId, Title) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, playlist.getAuthorId());
            stmt.setString(2, playlist.getTitle());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int playlistID = generatedKeys.getInt(1);
                        playlist.setPlaylistId(playlistID); // Set the generated PlaylistID
                        return playlistID; // Return the generated PlaylistID
                    }
                }
            }
        }
        return -1; // Return -1 if no playlistID is generated
    }

    // Update an existing playlist
    public void updatePlaylist(Playlist playlist, List<PlaylistContents> playlistContents) throws SQLException {
        String updatePlaylistQuery = "UPDATE Playlists SET AuthorId = ?, Title = ? WHERE PlaylistID = ?";
        String updatePlaylistContentQuery = "UPDATE PlaylistContents SET AudioId = ? WHERE PlaylistContentID = ?";

        try (Connection conn = getConnection();
             PreparedStatement updatePlaylistStmt = conn.prepareStatement(updatePlaylistQuery);
             PreparedStatement updatePlaylistContentStmt = conn.prepareStatement(updatePlaylistContentQuery)) {

            // Update the playlist itself
            updatePlaylistStmt.setInt(1, playlist.getAuthorId());
            updatePlaylistStmt.setString(2, playlist.getTitle());
            updatePlaylistStmt.setInt(3, playlist.getPlaylistId());
            updatePlaylistStmt.executeUpdate();

            // Update the playlist contents
            for (PlaylistContents playlistContent : playlistContents) {
                updatePlaylistContentStmt.setInt(1, playlistContent.getAudioId());
                updatePlaylistContentStmt.setInt(3, playlistContent.getPlaylistContentsId());
                updatePlaylistContentStmt.executeUpdate();
            }
        }
    }

    // Delete an playlist and its contents
    public void deletePlaylist(int playlistID) throws SQLException {
        String deletePlaylistContentsQuery = "DELETE FROM PlaylistContents WHERE PlaylistID = ?";
        String deletePlaylistQuery = "DELETE FROM Playlists WHERE PlaylistID = ?";

        try (Connection conn = getConnection();
             PreparedStatement deletePlaylistContentsStmt = conn.prepareStatement(deletePlaylistContentsQuery);
             PreparedStatement deletePlaylistStmt = conn.prepareStatement(deletePlaylistQuery)) {

            // Delete associated playlist contents
            deletePlaylistContentsStmt.setInt(1, playlistID);
            deletePlaylistContentsStmt.executeUpdate();

            // Delete the playlist itself
            deletePlaylistStmt.setInt(1, playlistID);
            deletePlaylistStmt.executeUpdate();
        }
    }

    // Retrieve an playlist by PlaylistID (with playlist contents)
    public Playlist getPlaylistById(int playlistID) throws SQLException {
        String query = "SELECT * FROM Playlists WHERE PlaylistID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, playlistID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Playlist playlist = new Playlist(
                        rs.getInt("PlaylistId"),
                        rs.getInt("AuthorId"),
                        rs.getString("Title")
                );
                playlist.setPlaylistContents(getPlaylistContents(playlistID)); // Fetch related playlist contents
                return playlist;
            }
        }
        return null;
    }

    // Retrieve all playlists for a specific user (with playlist contents)
    public List<Playlist> getPlaylistsByUserId(int userId) throws SQLException {
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM Playlists WHERE CustomerId = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int playlistID = rs.getInt("PlaylistId");
                String title = rs.getString("Title");

                Playlist playlist = new Playlist(playlistID, userId, title);
                playlist.setPlaylistContents(getPlaylistContents(playlistID));  // Fetch related playlist contents
                playlists.add(playlist);
            }
        }
        return playlists;
    }

    // Retrieve all playlists
    public List<Playlist> getAllPlaylists() throws SQLException {
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM Playlists";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                playlists.add(new Playlist(
                        rs.getInt("PlaylistId"),
                        rs.getInt("AuthorId"),
                        rs.getString("Title")
                ));
            }
        }
        return playlists;
    }

    // Retrieve playlist contents for a specific playlist
    private List<PlaylistContents> getPlaylistContents(int playlistId) throws SQLException {
        List<PlaylistContents> playlistContents = new ArrayList<>();
        String query = "SELECT * FROM PlaylistContents WHERE PlaylistID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, playlistId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PlaylistContents playlistContent = new PlaylistContents(
                        rs.getInt("PlaylistContentId"),
                        rs.getInt("PlaylistId"),
                        rs.getInt("AudioId")
                );
                playlistContents.add(playlistContent);
            }
        }
        return playlistContents;
    }
    
    public void deleteAllPlaylists() throws SQLException {
        String deletePlaylistContentsQuery = "DELETE FROM PlaylistContents";
        String deletePlaylistsQuery = "DELETE FROM Playlists";

        try (Connection conn = getConnection();
             PreparedStatement deletePlaylistContentsStmt = conn.prepareStatement(deletePlaylistContentsQuery);
             PreparedStatement deletePlaylistsStmt = conn.prepareStatement(deletePlaylistsQuery)) {

            // Delete all playlist contents
            deletePlaylistContentsStmt.executeUpdate();

            // Delete all playlists
            deletePlaylistsStmt.executeUpdate();
        }
    }
	
	
}