package jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.AudioDAO;
import jdbc.User;
import jdbc.PlaylistContentsDAO;
import jdbc.PlaylistDAO;

public class init_data{
	public static void main(String[] args) throws SQLException {
		AudioDAO audioDAO = new AudioDAO();
		PlaylistDAO playlistDAO = new PlaylistDAO();
		PlaylistContentsDAO playlistContentDAO = new PlaylistContentsDAO();
		UserDAO userDAO = new UserDAO();
	
		audioDAO.deleteAllAudios();
		playlistDAO.deleteAllPlaylists();
		playlistContentDAO.deleteAllPlaylistContents();
		userDAO.deleteAllUsers();
	
		System.out.println("Database emptied.");
		
		List<Audio> audio = new ArrayList<>();
		audio.add(new Audio(1, "british-soldiers-monologue", 3, "Podcast", "Monologue", "audios/british-soldiers-monologue.wav"));
		audio.add(new Audio(2, "funny-country-loop", 1, "Song", "Country", "audios/funny-country-loop.wav"));
		
		List<User> user = new ArrayList<>();
		user.add(new User(1, "user1", "pass1", "email1@gmail.com", "Rock"));
		user.add(new User(2, "user2", "pass2", "email2@gmail.com", "EDM"));
		user.add(new User(3, "user3", "pass3", "email3@gmail.com", "Rap"));
		
		List<Playlist> playlist = new ArrayList<>();
		playlist.add(new Playlist(1,1,"My First Playlist"));
		
		List<PlaylistContents> playlistContents = new ArrayList<>();
		playlistContents.add(new PlaylistContents(1,1,1));
		playlistContents.add(new PlaylistContents(2,1,2));
		
	}
}