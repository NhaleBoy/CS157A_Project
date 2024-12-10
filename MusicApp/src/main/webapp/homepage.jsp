
<%@ page import="jdbc.*, java.util.List, java.util.Arrays, jdbc.Audio"%>

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
		<button class="tablinks" onclick="openTab(event, 'Settings')">Settings</button>
	</div>
	
	<div id="Search" class="tabcontent">
		<h3>Search</h3>
		<form method="POST" action=Search>
			<input type="text" name=searchbox id=searchbox>
		    <br><br>
		    <label for="mediaType">Media Type:</label>
		    <select name="mediaType", id="mediaType">
		    	<option value="Song">Song</option>
		    	<option value="Podcast">Podcast</option>
		    </select>
		    <br><br>
			<input type="submit" value="Confirm">
		</form>
		<%
			Audio audio = (Audio) request.getAttribute("search");
			if (audio != null) {
				String title = audio.getTitle();
				String path = audio.getFilePath();
	    	%>
	        <%= title%>: <audio controls><source src=<%=path%> type="audio/wav"></audio>
	    	<%
	        	}
	    	%>
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
                                if (contents != null && !contents.isEmpty()) {
                                    for (PlaylistContents playlistContent : contents) {
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
        <form method="post" action=CreatePlaylist>
            <label for="title">Playlist Title:</label>
            <input type="text" id="title" name="title">
            
            <label for="songTitle">Song Titles:</label>
            <input type="text" id="songTitle" name="songTitle">
            
            <button type="submit">Create Playlist</button>
        </form>
        <%
			String message = (String) request.getAttribute("message");
			if (message != null) {
		%>
		<div><b><%= message %></b></div>
		<%
			}
		%>
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