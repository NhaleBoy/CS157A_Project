
<%@ page import="jdbc.*, java.util.List, java.util.Arrays"%>

<!DOCTYPE html>
<html>
<head>
<style>.playlists-container{
	max-height: 500px; /* Set max height for the container */
    overflow-y: auto;
	}
</style>
<meta charset="UTF-8">
<link rel="stylesheet" href="webpage.css" type="text/css">
<title>Homepage</title>
</head>
<body>
	<%
		if(session.getAttribute("username") == null){
			response.sendRedirect("index.jsp");
		}
	%>
	<h2>MusicApp</h2>
	<div class="tab">
		<button class="tablinks" onclick="openTab(event, 'Search')">Search</button>
		<button class="tablinks" onclick="openTab(event, 'Playlists')">Playlists</button>
		<button class="tablinks" onclick="openTab(event, 'CreatePlaylist')">Create_Playlist</button>
		<button class="tablinks" onclick="openTab(event, 'Artists')">Artists</button>
		<button class="tablinks" onclick="openTab(event, 'Settings')">Settings</button>
	</div>
	
	<div id="Search" class="tabcontent">
		<h3>Search</h3>
		<form method=post action=Search>
			<input type="text" name=searchbox id=searchbox>
			<br><br>
			<label for="searchBy">Search by artist or song title:</label>
		    <select name=searchBy, id=searchBy>
		    	<option value="Artist">Artist</option>
		    	<option value="Title">Title</option>
		    </select>
		    <br><br>
		    <label for="mediaType">Media Type:</label>
		    <select name=mediaType, id=mediaType>
		    	<option value="Any">Any</option>
		    	<option value="Song">Song</option>
		    	<option value="Podcast">Podcast</option>
		    </select>
		    <br><br>
			<input type=submit value="Confirm">
		</form>
	</div>

	<div id="Playlists" class="tabcontent">
		<h3>Playlists</h3>
		<%
        // Display all playlists
        PlaylistDAO playlistDAO = new PlaylistDAO();
        PlaylistContentsDAO playlistContentsDAO = new PlaylistContentsDAO();
        UserDAO userDAO = new UserDAO();
        AudioDAO audioDAO = new AudioDAO();
        try {
            List<Playlist> playlists = playlistDAO.getAllPlaylists();
            if (playlists != null && !playlists.isEmpty()) {
                for (Playlist playlist : playlists) {
                    List<PlaylistContents> contents = playlist.getPlaylistContents();
    %>
                    <div class="playlist">
                        <h2><%= playlist.getTitle() %></h2>
                        <p><strong>Author Username:</strong> <%= userDAO.getUsernameViaUserId(playlist.getAuthorId())%></p>
                        <p><strong>Contents:</strong></p>
                        <ul>
                            <% 
                                List<PlaylistContents> audioNames = playlistDAO.getPlaylistContents(playlist.getPlaylistId());
                                if (audioNames != null && !audioNames.isEmpty()) {
                                    for (PlaylistContents playlistContent : audioNames) {
                                    	String audioname = audioDAO.getAudioById(playlistContent.getAudioId()).getTitle();
                                    	
                            %>
                                        <li><%= audioname %></li>
                            <% 
                                    }
                                } else { 
                            %>
                                <li>No audio files in this playlist.</li>
                            <% } %>
                        </ul>
                    </div>
    <%
                }
            } else {
    %>
                <p>No playlists available.</p>
    <%
            }
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    %>
	</div>
	
	<div id="CreatePlaylist" class="tabcontent">
	<h3>Create a New Playlist</h3>
        <form method="post" action="homepage.jsp">
            <label for="title">Playlist Title:</label>
            <input type="text" id="title" name="title" required>
            
            <label for="authorId">Author ID:</label>
            <input type="number" id="authorId" name="authorId" required>
            
            <button type="submit">Create Playlist</button>
        </form>
        <%
            // Handle playlist creation
            if (request.getMethod().equalsIgnoreCase("POST")) {
                String title = request.getParameter("Playlist Title");
                String dirtyContents = request.getParameter("Comma Separated List of Audio Titles");
                if (title != null && !title.isEmpty()) {
                    try {
                    	PlaylistDAO playlist1DAO = new PlaylistDAO();
                        PlaylistContentsDAO playlist1ContentsDAO = new PlaylistContentsDAO();
                        AudioDAO ADAO = new AudioDAO();
                        int authorId =  (int) request.getSession().getAttribute("userId");
                        //make sure to handle exceptions and empty playlist content
                        Playlist newPlaylist = new Playlist(0, authorId, title); // Playlist ID will be auto-generated
                        int generatedId = playlist1DAO.addPlaylist(newPlaylist);
                        if (generatedId > 0) {
                            out.println("<p style='color: green;'>Playlist created successfully!</p>");
                        } else {
                            out.println("<p style='color: red;'>Failed to create playlist.</p>");
                        }
                        
                        
                        //separate dirtycontents into list of audio names
                        if(dirtyContents != null && !dirtyContents.isEmpty()){
                        	List<String> songTitles = Arrays.asList(dirtyContents.split("\\s*,\\s*"));
                        	for(String songtitle: songTitles){
                        		int audioid = ADAO.getAudioIdByTitle(songtitle);
                        		if(audioid == -1){
                        			throw new Exception("not a valid song title");
                        		}
                        		PlaylistContents songti = new PlaylistContents(0,newPlaylist.getPlaylistId(),audioid);
                        		playlist1ContentsDAO.addPlaylistContents(songti);
                        	}
                        }
                    } catch (Exception e) {
                        out.println("<p style='color: red;'>Error: " + e.getMessage() + "</p>");
                    }
                } else {
                    out.println("<p style='color: red;'>Please provide valid input.</p>");
                }
            }
        %>
	</div>

	<div id="Artists" class="tabcontent">
		<h3>Artists</h3>
	</div>

	<div id="Settings" class="tabcontent">
		<h3>Settings</h3>
		<form method=post action=Settings>
			<label for="genres">Choose preferred genre:</label>
		    <select name=genres, id=genres>
		    	<option value="n/a">N/A</option>
		    	<option value="Rock">Rock</option>
		    	<option value="Country">Country</option>
		    	<option value="Metal">Metal</option>
		    	<option value="K-pop">K-pop</option>
		    	<option value="Rap">Rap</option>
		    </select>
			<input type=submit value="Confirm">
		</form>
		<br><br>
		<form method=post action=Settings>
			<input type=submit name=creatorpage value="Creator Mode">
		</form>
		<br><br>
		<form method=post action=Settings>
			<input type=submit name=logout value="Logout">
		</form>
	</div>
	
	<script>
	function openTab(evt, tabName) {
	  var i, tabcontent, tablinks;
	  tabcontent = document.getElementsByClassName("tabcontent");
	  for (i = 0; i < tabcontent.length; i++) {
	    tabcontent[i].style.display = "none";
	  }
	  tablinks = document.getElementsByClassName("tablinks");
	  for (i = 0; i < tablinks.length; i++) {
	    tablinks[i].className = tablinks[i].className.replace(" active", "");
	  }
	  document.getElementById(tabName).style.display = "block";
	  evt.currentTarget.className += " active";
	}
	</script>
</body>
</html>