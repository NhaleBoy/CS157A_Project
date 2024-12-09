<%@ page import="jdbc.AudioDAO, jdbc.Audio, java.util.ArrayList, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="webpage.css" type="text/css">
<title>Creator Upload</title>
</head>
<body>
	<%
		if(session.getAttribute("username") == null){
			response.sendRedirect("index.jsp");
		}
	%>
	<h2>MusicApp</h2>
	<div class="tab">
		<button class="tablinks" onclick="openTab(event, 'Songs')">Songs</button>
		<button class="tablinks" onclick="openTab(event, 'Upload')">Upload</button>
		<button class="tablinks" onclick="openTab(event, 'Podcasts')">Podcasts</button>
	</div>
	
	<div id="Upload" class="tabcontent">
		<h3>Upload</h3>
		<form method=post action=Upload enctype="multipart/form-data">
			<input type="file" name="file">
			<br>
			<label for="mediaType">Select type:</label>
			<select name=mediaType, id=mediaType>
		    	<option value="Song">Song</option>
		    	<option value="Podcast">Podcast</option>
		    </select>
			<br>
			<label for="genre">Select genre:</label>
		    <select name=genre, id=genres>
		    	<option value="n/a">N/A</option>
		    	<option value="rock">Rock</option>
		    	<option value="country">Country</option>
		    </select>
			<br>
			<input type=submit value="Upload">
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

	<div id="Songs" class="tabcontent">
		<h3>Songs</h3>
		<%
			int userId = (int) request.getSession().getAttribute("userId");
			AudioDAO dao = new AudioDAO();
			 List<Audio> audios = dao.getAudiosByAuthor(userId);
			for(Audio audio: audios) {
				String title = audio.getTitle();
				String path = audio.getFilePath();
		%>
		<br>
		<%= title%>: <audio controls><source src=<%=path%> type="audio/wav"></audio>
		<br>
		<%
			}
		%>
	</div>

	<div id="Podcasts" class="tabcontent">
		<h3>Podcasts</h3>
		<%
			 List<Audio> podcasts = dao.getAudiosByAuthor(userId);
			for(Audio audio: podcasts) {
				String title = audio.getTitle();
				String path = audio.getFilePath();
		%>
		<br>
		<%= title%>: <audio controls><source src=<%=path%> type="audio/wav"></audio>
		<br>
		<%
			}
		%>
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