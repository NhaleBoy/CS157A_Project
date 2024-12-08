package jdbc;

public class User {
	private int userId;
	private String username;
	private String password;
	private String email;
	private String prefGenre;

	public User(int userId, String username, String password, String email, String prefGenre) {
		this.userId = userId;
		this.email = email;
		this.username = username;
		this.password = password;
		this.prefGenre = prefGenre;
	}
	
	public String getUsername() {
		return username;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrefGenre() {
		return prefGenre;
	}

	public void setPrefGenre(String prefGenre) {
		this.prefGenre = prefGenre;
	}
	
	

}
