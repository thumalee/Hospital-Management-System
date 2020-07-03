 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>

<head>
	<title>Appointment Tracker App</title>
	
	<link type = "text/css" rel = "stylesheet" href="css/style.css">
</head>

<%
response.setHeader("Cache-Control","no-cache, no-store,must-revealidate");
if(session.getAttribute("email")==null)
	{
		response.sendRedirect("login.jsp");
	}
	%>

<body>
<div class="lout">
<form action="UserLogoutControllerServlet">
<input type="submit" value="Logout">
</form>
</div>
	<div id = "wrapper">
		<div id = "header">
			<h2>Hospital</h2>
		</div>
	</div>
	
<div id = "container">
		<div id = "content">
		
			<table>
				<tr>
					<th>Appointment ID</th>
					<th>Patient ID</th>
					<th>Doctor ID</th>
					<th>Date</th>
					<th>Room</th>
				
				</tr>
				
				<c:forEach var = "tempAppointment" items = "${APPOINTMENT_LIST}">
				
					<!-- set up a link for each appointmnet -->
					<c:url var = "tempLink" value = "AppointmentControllerServlet">
						<c:param name = "command" value = "LOAD"/>
						<c:param name = "appointmentId" value = "${tempAppointment.appointmentId}"/>
					</c:url>
					
				
					
					<tr>
						<td>${tempAppointment.appointmentId}</td>
						<td>${tempAppointment.patientId}</td>
						<td>${tempAppointment.doctorId}</td>
						<td>${tempAppointment.date}</td>
						<td>${tempAppointment.room}</td>
				
					</tr>
				</c:forEach>
			</table>
		</div>
	
	</div>
</body>
</html>

