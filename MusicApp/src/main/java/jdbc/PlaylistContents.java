package jdbc;

public class PlaylistContents {
	private int playlistContentsId;
	private int playlistId;
	private int audioId;
	
	
	public PlaylistContents(int playlistContentsId, int playlistId, int audioId) {
		this.playlistContentsId =playlistContentsId;
		this.playlistId =playlistId;
		this.audioId = audioId;
	}
	
	public PlaylistContents( int playlistId, int audioId) {
		this.playlistId =playlistId;
		this.audioId = audioId;
	}
	
	@Override
	public String toString() {
		return "PlaylistContents [playlistContentsId=" + playlistContentsId + ", playlistId="+playlistId+", AudioId="+audioId+"]";
	}
	
	public int getPlaylistContentsId() {
		return playlistContentsId;
	}
	public void setPlaylistContentsId(int playlistContentsId) {
		this.playlistContentsId = playlistContentsId;
	}
	public int getPlaylistId() {
		return playlistId;
	}
	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}
	public int getAudioId() {
		return audioId;
	}
	public void setAudioId(int audioId) {
		this.audioId = audioId;
	}
	
	
}
