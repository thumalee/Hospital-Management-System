<!DOCTYPE html>

<html>
<head>
<Title>Hospital managament System  </Title>
<link type="text/css" rel="stylesheet" href="css/admindash.css">
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

<div id="wrap">
	<div id="header">
	<h1>Hospital Management System</h1>
	</div>

</div>

<div class="admin">
	<h2>Admin Dash Board</h2>
</div>


<div class="container">
<a href="docSelect.jsp">
<img class="doc" src="images/doctor.png" alt="doc">
</a>
<div class="middle">
    <div class="text">Doctor</div>
  </div>
</div>

<div class="container">
<a href="PharmacistControllerServlet">
<img class="pharma" src="images/pharma.png" alt="pharma">
</a>
<div class="middle">
    <div class="text">Pharmacist</div>
  </div>
</div>

<div class="container">
<a href="LabAssistantControllerServlet">
<img class="lab" src="images/lab.png" alt="lab">
</a>
<div class="middle">
    <div class="text">Lab Technician</div>
  </div>
</div>

<div class="container">
<a href="ReceptionistControllerServlet">
<img class="recep" src="images/receps.png" alt="recep">
</a>
<div class="middle">
    <div class="text">Receptionist</div>
  </div>
</div>





</body>


</html>

