<!DOCTYPE html>

<html>
	
<head>
	<title>Update Appointment</title>
	
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
		<div id = "container">
			<h2>Hospital</h2>
		</div>
	</div>
	
	<div id = "container">
		<h3>Update Appointment</h3>
		
		<form action = "AppointmentControllerServlet" method = "GET" data-parsley-validate="">	
		
		<input type = "hidden" name = "command" value = "UPDATE"/>
		
		<input type = "hidden" name = "appointmentId" value = "${THE_APPOINTMENT.appointmentId }"/>
		
		<table>
			<tbody>
			
			<tr>
					<td><label>Doctor ID : </label></td>
					<td><input type = "text" name = "did"
								value = "${THE_APPOINTMENT.doctorId}"
								required data-parsley-type="digits"/></td>
				</tr>
				
				<tr>
					<td><label>Date : </label></td>
					<td><input type = "text" name = "date"
								value = "${THE_APPOINTMENT.date}"
								/></td>
				</tr>
				
				<tr>
					<td><label>Room : </label></td>
					<td><input type = "text" name = "room"
								value = "${THE_APPOINTMENT.room}"
								required data-parsley-type="digits"/></td>
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