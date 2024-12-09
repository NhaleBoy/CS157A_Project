package jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Playlist {
	private int playlistId;
	private int authorId;
	private String title;
	private List<PlaylistContents> playlistContents;
	private int audioCount;
	private AudioDAO audioDAO;
	
	
	//construct without playlist contents
	public Playlist (int playlistId, int authorId, String title) {
		this.setPlaylistId(playlistId);
		this.authorId = authorId;
		this.title = title;
		this.audioCount = 0;
		this.setPlaylistContents(new ArrayList<>());
		this.audioDAO = new AudioDAO();
	}
	
	//contruct with playlist contents
	public Playlist (int playlistId, int authorId, String title, List<PlaylistContents> playlistContents) {
		this.setPlaylistId(playlistId);
		this.authorId = authorId;
		this.title = title;
		this.audioCount = 0;
		this.setPlaylistContents(playlistContents);
		this.audioDAO = new AudioDAO();
	}//ponentially delete
	
	public List<String> getPlaylistAudioNames() throws SQLException {
		List<String> audioNames = new ArrayList<>();
		for (PlaylistContents content: playlistContents) {
			Audio audio = audioDAO.getAudioById(content.getAudioId());
			if (audio != null) {
				audioNames.add(audio.getTitle());
			}
		}
		return audioNames;
	}
	
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}



	public int getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	public List<PlaylistContents> getPlaylistContents() {
		return playlistContents;
	}

	public void setPlaylistContents(List<PlaylistContents> playlistContents) {
		this.playlistContents = playlistContents;
	}
	
}
