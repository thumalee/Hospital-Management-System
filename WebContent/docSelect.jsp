<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
a {
  margin-left:20px;
  background: #0b7dda;;
  border: none;
  width:200px;
  border-radius: 12px;
  color: white;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 19px;

}
a:hover
{
	background-color: #2196F3;
}

form{
	border:1px solid ;
	width:240px;
	height:280px;
	margin-top:5%;
	margin-left:40%;
	border-radius:6px;
}
</style>
<link type="text/css" rel="stylesheet" href="css/style.css">
<meta charset="ISO-8859-1">
<title>Doctors</title>
</head>

<%
response.setHeader("Cache-Control","no-cache, no-store,must-revealidate");
if(session.getAttribute("email")==null)
	{
		response.sendRedirect("login.jsp");
	}
	%>


<div class="lout">
<form action="UserLogoutControllerServlet">
<input type="submit" value="Logout">
</form>
</div>


<body>
<div id = "wrapper">
		<div id ="header">
		<h2 align="center">Welcome ${email}
			Select doctor type </h2>
		</div>
	</div>
<h2 align="center">Doctors</h2>
<form>
<b>If you want to see,</b>
<br></br>
<a href="PermanentDocControllerServ"><h4> Permanent doctors list</h4></a>
<br></br>
<a href="VisitingDocControllerServ"><h4> Visiting doctors list</h4></a>
</form>
</body>
</html>