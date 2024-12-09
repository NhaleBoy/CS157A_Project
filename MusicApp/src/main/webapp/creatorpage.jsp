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
			//response.sendRedirect("index.jsp");
		}
	%>
	<h2>MusicApp</h2>
	<div class="tab">
		<form action="" method="post" enctype="multipart/form-data">
		<input type="file" name="audioFile"/>
		<input type="submit" value="Upload Audio" name="save_audio"/>
		</form>
		<button class="tablinks" onclick="openTab(event, 'Songs')">Songs</button>
		<button class="tablinks" onclick="openTab(event, 'Upload')">Upload</button>
		<button class="tablinks" onclick="openTab(event, 'Podcasts')">Podcasts</button>
	</div>
	
	<div id="Upload" class="tabcontent">
		<h3>Upload</h3>
		
	</div>

	<div id="Songs" class="tabcontent">
		<h3>Songs</h3>
	</div>

	<div id="Podcasts" class="tabcontent">
		<h3>Podcasts</h3>
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