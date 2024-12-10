package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class initialize_data{
	public static void main(String[] args) throws SQLException {
		Database dao = new Database();
		Connection conn = dao.getConnection();
		AudioDAO audioDAO = new AudioDAO();
		PlaylistDAO playlistDAO = new PlaylistDAO();
		PlaylistContentsDAO playlistContentDAO = new PlaylistContentsDAO();
		UserDAO userDAO = new UserDAO();
		
		Statement stmt = conn.createStatement();
		stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");
	
		audioDAO.deleteAllAudios();
		playlistDAO.deleteAllPlaylists();
		playlistContentDAO.deleteAllPlaylistContents();
		userDAO.deleteAllUsers();
	
		System.out.println("Database emptied.");
		
		List<Audio> audio = new ArrayList<>();
		audio.add(new Audio(1, "british-soldiers-monologue", 3, "Podcast", "Monologue", "upload\\british-soldiers-monologue.wav"));
		audio.add(new Audio(2, "funny-country-loop", 1, "Song", "Country", "upload\\funny-country-loop.wav"));
		audio.add(new Audio(3, "baby_crying", 12, "Podcast", "Acting", "upload\\baby_crying.wav"));
		audio.add(new Audio(4, "baby_cry", 12, "Podcast", "Monologue", "upload\\baby_cry.wav"));
		audio.add(new Audio(5, "yama-rock1", 10, "Song", "Rock", "upload\\yama-rock1.wav"));
		audio.add(new Audio(6, "rock_guitar1", 10, "Song", "Rock", "upload\\rock_guitar1.wav"));
		audio.add(new Audio(7, "funny-country-loop", 9, "Song", "Country", "upload\\funny-country-loop.wav"));
		audio.add(new Audio(8, "construction", 11, "Song", "Metal", "upload\\construction.wav"));
		audio.add(new Audio(9, "chime_big_ben", 9, "Song", "UK Garage", "upload\\chime_big_ben.wav"));
		audio.add(new Audio(10, "cheer_long", 10, "Song", "K-Pop", "upload\\cheer_long.wav"));
		audio.add(new Audio(11, "chainsaw", 10, "Song", "Rap", "upload\\chainsaw.wav"));
		audio.add(new Audio(12, "ahem_x", 13, "Podcast", "Monologue", "upload\\ahem_x.wav"));
		audio.add(new Audio(13, "cash_register2", 10, "Song", "Rap", "upload\\cash_register2.wav"));
		audio.add(new Audio(14, "car_crash", 14, "Podcast", "True Crime", "upload\\car_crash.wav"));
		audio.add(new Audio(15, "car_crash2", 14, "Song", "True Crime", "upload\\car_crash2.wav"));
		
		List<User> user = new ArrayList<>();
		user.add(new User(1, "user1", "pass1", "email1@gmail.com", "Rock"));
		user.add(new User(2, "user2", "pass2", "email2@gmail.com", "EDM"));
		user.add(new User(3, "user3", "pass3", "email3@gmail.com", "Rap"));
		user.add(new User(4, "user4", "pass4", "email4@gmail.com", "Techno"));
		user.add(new User(5, "user5", "pass5", "email5@gmail.com", "R&B"));
		user.add(new User(6, "user6", "pass6", "email6@gmail.com", "Ambient"));
		user.add(new User(7, "user7", "pass7", "email7@gmail.com", "Country"));
		user.add(new User(8, "user8", "pass8", "email8@gmail.com", "House"));
		user.add(new User(9, "user9", "pass9", "email9@gmail.com", "UK Garage"));
		user.add(new User(10, "user10", "pass10", "email10@gmail.com", "K-Pop"));
		user.add(new User(11, "coolguy123", "password", "coolgu7@yahoo.com", "Soundtrack"));
		user.add(new User(12, "NathanJ", "passw0rd", "realemail@gmail.com", "Rap"));
		user.add(new User(13, "Benny1e", "ilovekpop", "Benny@gmail.com", "K-Pop"));
		user.add(new User(14, "testuser", "test", "test@gmail.com", "EDM"));
		user.add(new User(15, "user15", "pass15", "email15@gmail.com", "Rap"));
		
		List<Playlist> playlist = new ArrayList<>();
		playlist.add(new Playlist(1,1,"My First Playlist"));
		playlist.add(new Playlist(2,2,"EDM beats"));
		playlist.add(new Playlist(3,3,"hard music"));
		playlist.add(new Playlist(4,4,"podcaster"));
		playlist.add(new Playlist(5,5,"sleepy mix"));
		playlist.add(new Playlist(6,6,"liked music"));
		playlist.add(new Playlist(7,7,"fav songs"));
		playlist.add(new Playlist(8,8,"music"));
		playlist.add(new Playlist(9,9,"london is my city"));
		playlist.add(new Playlist(10,1,"My second Playlist"));
		playlist.add(new Playlist(11,11,"soundtrack of my life"));
		playlist.add(new Playlist(12,12,"playlist swag"));
		playlist.add(new Playlist(13,13,"I love k-pop"));
		playlist.add(new Playlist(14,14,"empty playlist"));
		playlist.add(new Playlist(15,15,"superlist"));
		
		List<PlaylistContents> playlistContents = new ArrayList<>();
		playlistContents.add(new PlaylistContents(1,1,1));
		playlistContents.add(new PlaylistContents(2,1,2));
		playlistContents.add(new PlaylistContents(3,1,3));
		playlistContents.add(new PlaylistContents(4,1,4));
		playlistContents.add(new PlaylistContents(5,1,5));
		playlistContents.add(new PlaylistContents(6,1,6));
		playlistContents.add(new PlaylistContents(7,2,11));
		playlistContents.add(new PlaylistContents(8,2,12));
		playlistContents.add(new PlaylistContents(9,3,11));
		playlistContents.add(new PlaylistContents(10,3,11));
		playlistContents.add(new PlaylistContents(11,4,2));
		playlistContents.add(new PlaylistContents(12,5,3));
		playlistContents.add(new PlaylistContents(13,5,1));
		playlistContents.add(new PlaylistContents(14,5,14));
		playlistContents.add(new PlaylistContents(15,6,5));
		playlistContents.add(new PlaylistContents(16,7,1));
		playlistContents.add(new PlaylistContents(17,7,2));
		playlistContents.add(new PlaylistContents(18,8,10));
		playlistContents.add(new PlaylistContents(19,8,2));
		playlistContents.add(new PlaylistContents(20,8,13));
		playlistContents.add(new PlaylistContents(21,9,11));
		playlistContents.add(new PlaylistContents(22,9,2));
		playlistContents.add(new PlaylistContents(23,9,3));
		playlistContents.add(new PlaylistContents(24,10,4));
		playlistContents.add(new PlaylistContents(25,10,5));
		playlistContents.add(new PlaylistContents(26,10,7));
		playlistContents.add(new PlaylistContents(27,11,12));
		playlistContents.add(new PlaylistContents(28,11,8));
		playlistContents.add(new PlaylistContents(29,11,9));
		playlistContents.add(new PlaylistContents(30,12,7));
		playlistContents.add(new PlaylistContents(31,12,14));
		playlistContents.add(new PlaylistContents(32,12,15));
		playlistContents.add(new PlaylistContents(33,13,7));
		playlistContents.add(new PlaylistContents(34,13,5));
		playlistContents.add(new PlaylistContents(35,13,13));
		playlistContents.add(new PlaylistContents(36,13,4));
		playlistContents.add(new PlaylistContents(37,15,11));
		playlistContents.add(new PlaylistContents(38,15,12));
		playlistContents.add(new PlaylistContents(39,15,3));
		playlistContents.add(new PlaylistContents(40,15,11));
		
		for(User u: user)
		{
			userDAO.addUser(u);
		}
		for(Audio a: audio)
		{
			audioDAO.addAudio(a);
		}
		for(Playlist p: playlist){
			playlistDAO.addPlaylist(p);
		}
		for(PlaylistContents pc: playlistContents)
		{
			playlistContentDAO.addPlaylistContents(pc);
		}
		stmt.execute("SET FOREIGN_KEY_CHECKS = 1;");
		System.out.println("Database filled.");

		
	}
}