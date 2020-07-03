<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Successful</title>

<link type="text/css" rel="stylesheet" href="css/registersuccess.css">

 <script>
         setTimeout(function(){
            window.location.href = 'login.jsp';
         }, 5000);
      </script>

</head>

<body>

<div id="wrap">
	<div id="header">
	<h1>Hospital Management System</h1>
	</div>

</div>
<form>
<div>
<img src="images/Tick.png">
<div class="text">
<h1 ><b>Thank you !</b></h1>
<h2 >Registration Completed Succesfully</h2><br>
<h3 >You will be redirected to the login page in 5 seconds</h3>
</div>
</div>

</form>
</body>
</html>