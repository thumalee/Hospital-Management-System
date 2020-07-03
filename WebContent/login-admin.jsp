 <!Doctype html>
<html>
<head>

<Title>Hospital managament System-Login  </Title>
<link type="text/css" rel="stylesheet" href="css/login.css">
</head>

</head>
<body>

<div id="wrap">
	<div id="header">
	<h1>Hospital Management System</h1>
	</div>
</div>

<div class="regtop">
<h2>Admin Login</h2>
</div>

<div class="container">


	<form action="LoginControllerServlet" method="get">
		 <label for="email"><b>Enter Email</b></label>
		  <input type="text" placeholder="Enter Email" name="email">
	<label for="psw"><b>Enter Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required>
	 <button type="submit" class="registerbtn">Login</button>
	
	
	
	</form>
</div>	
	
<div class="container signin">
    <p>No account <a href="user-registration.jsp">Register</a>.</p>
  </div>

</body>


</html>

