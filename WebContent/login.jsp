 <!Doctype html>
<html>
<head>

<Title>Hospital managament System-Login  </Title>
<link type="text/css" rel="stylesheet" href="css/login.css">
<link type="text/css" rel="stylesheet" href="css/parsley.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="JavaScript/parsley.min.js"></script>

</head>

</head>
<body>

<div id="wrap">
	<div id="header">
	<h1>Hospital Management System</h1>
	</div>
</div>

<a href="index.jsp">
<img style="height:60px; margin-left:110px;position:absolute;margin-top:37px" src="images/home.png" alt="home">
</a>



<div class="regtop">
<h2>User Login</h2>
</div>

<div class="container">


	<form action="LoginControllerServlet" method="get"  data-parsley-validate="">
		 <label for="email"><b>Enter Email</b></label>
		  <input type="text" placeholder="Enter Email" name="email"  required  data-parsley-type="email">
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

