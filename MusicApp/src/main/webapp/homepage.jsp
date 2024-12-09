<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="webpage.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<%
		if(session.getAttribute("username") == null){
			//response.sendRedirect("index.jsp");
		}
	%>
	<h2>MusicApp</h2>
	<div class="tab">
	  <button class="tablinks" onclick="openTab(event, 'Playlists')">Playlists</button>
	  <button class="tablinks" onclick="openTab(event, 'Artists')">Artists</button>
	  <button class="tablinks" onclick="openTab(event, 'Settings')">Settings</button>
	</div>
	
	<div id="Playlists" class="tabcontent">
	  <h3>Playlists</h3>
	  <%
	  	
	  %>>
	</div>
	
	<div id="Artists" class="tabcontent">
	  <h3>Artists</h3>
	  <%
	  	
	  %>>
	</div>
	
	<div id="Settings" class="tabcontent">
	  <h3>Settings</h3>
	  <%
	  	
	  %>>
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