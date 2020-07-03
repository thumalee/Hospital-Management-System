<!DOCTYPE html>

<html>
	
<head>
	<title>Add Appointment</title>
	
	<link type = "text/css" rel = "stylesheet" href = "css/style.css">
	<link type = "text/css" rel = "stylesheet" href = "css/add-style.css">
	<link type="text/css" rel="stylesheet" href="css/parsley.css">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="JavaScript/parsley.min.js"></script>
	
</head>

<%
response.setHeader("Cache-Control","no-cache, no-store,must-revealidate");
if(session.getAttribute("email")==null)
	{
		response.sendRedirect("login.jsp");
	}
	%>

<body>
	<div id = "wrapper">
		<div id = "container"  >
			<h2 style="color: #000;">Hospital</h2>
		</div>
	</div>
	
	<div id = "container">
		<h3 style="color: #000;">Add Appointment</h3>
		
		<form action = "AppointmentControllerServlet" method = "GET" data-parsley-validate="">	
		
		<input type = "hidden" name = "command" value = "ADD"/>
		
		<table>
			<tbody>
			
				<tr>
					<td><label>Appointment ID : </label></td>
					<td><input type = "text" name = "aid" required data-parsley-type="digits"/></td>
				</tr>
				
				<tr>
					<td><label>Patient ID : </label></td>
					<td><input type = "text" name = "pid" required data-parsley-type="digits"/></td>
				</tr>
				
				<tr>
					<td><label>Doctor ID : </label></td>
					<td><input type = "text" name = "did" required /></td>
				</tr>
				
				<tr>
					<td><label>Date : </label></td>
					<td><input type = "text" name = "date"/></td>
				</tr>
				
				<tr>
					<td><label>Room : </label></td>
					<td><input type = "text" name = "room" required data-parsley-type="digits"/></td>
				</tr>
				
				<tr>
					<td><label></label></td>
					<td><input type ="submit" value = "Save" class = "save"/></td>
				</tr>
				
			</tbody>
		</table>
		</form>
		
		<div style = "clear : both ;"></div>
		
			<p>
				<a href = "AppointmentControllerServlet">Back to list</a>
			</p>
		
	</div>
</body>
</html>