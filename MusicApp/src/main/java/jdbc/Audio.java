package jdbc;

public class Audio {
	private int AudioId;
    private String Title;
    private String FilePath;
    private int AuthorId;
    private String Category;
    private String Genre;

    
    
    public Audio(int AudioID, String Title, int AuthorId, String Category, String Genre, String FilePath) {
    	this.AudioId = AudioID;
    	this.Title = Title;
    	this.AuthorId = AuthorId;
    	this.Category = Category;
    	this.Genre = Genre;
    	this.FilePath = FilePath;

    	
    	
    }
    
    
	public int getAudioID() {
		return AudioId;
	}
	public void setAudioID(int AudioId) {
		this.AudioId = AudioId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public int getAuthorId() {
		return AuthorId;
	}
	public void setAuthorId(int authorId) {
		AuthorId = authorId;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}


	public String getFilePath() {
		return FilePath;
	}


	public void setFilePath(String filePath) {
		FilePath = filePath;
	}

}
