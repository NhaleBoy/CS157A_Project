<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="login.css" type="text/css">
<title>Please enter Login/Sign up</title>
</head>
<body>
	<%
		if(session.getAttribute("username") != null){
			response.sendRedirect("homepage.jsp");
		}
	%>
	<div class=center>
		<div class=login>
			<h1>Login</h1>
			<form method=post action=login>
			<input type=text name=username> Username
			<br>
			<input type=text name=password> Password
			<br><br>
			<input type=submit value="Login">
			</form>
			
		</div>
		<div class=signup>
			<h1>Sign Up</h1>
			<form method=post action=signup>
			<input type=text name=sUsername> Username
			<br>
			<input type=text name=sPassword> Password
			<br>
			<input type=text name=email> E-mail
			<br><br>
			<input type=submit value="Sign Up">
			</form>
		</div>
		<div class=message>
			<%
	        	String message = (String) request.getAttribute("message");
	        if (message != null) {
	    	%>
	        <div><b><%= message %></b></div>
	    	<%
	        	}
	    	%>
    	</div>
	</div>
	
</body>
</html>