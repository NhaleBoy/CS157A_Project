<!DOCTYPE html>
<html>
<head>
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
			<label for="genreSearch">Search by artist or song title:</label>
		    <select name=genreSearch, id=genreSearch>
		    	<option value="n/a">N/A</option>
		    	<option value="Rock">Rock</option>
		    	<option value="Country">Country</option>
		    	<option value="Metal">Metal</option>
		    	<option value="K-pop">K-pop</option>
		    	<option value="Rap">Rap</option>
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