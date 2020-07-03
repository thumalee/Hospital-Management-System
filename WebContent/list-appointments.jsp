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


<div class="lout">
<form action="UserLogoutControllerServlet">
<input type="submit" value="Logout">
</form>
</div>




<body>
	<div id = "wrapper">
		<div id = "header">
			<h2>Hospital</h2>
		</div>
	</div>
	
	
		<input type = "button" value = "Add Bill" 
					onclick = "window.location.href = 'BillControllerServlet'; return false;"
						class = "add-bill-button"
						
			/>
			
		<input type = "button" value = "Add Patient" 
					onclick = "window.location.href = 'add-patient-form.jsp'; return false;"
					class = "add-patient-button"
			/>
	
	<div id = "container">
		<div id = "content">
		
			<!-- put new button : Add Appointment -->
			<input type = "button" value = "Add Appointment" 
					onclick = "window.location.href = 'add-appointment-form.jsp'; return false;"
						style="border: 1px solid #666; 
							border-radius: 5px; 
							padding: 4px; 
							font-size: 12px;
							font-weight: bold;
							width: 120px; 
							padding: 5px 10px; 
							margin-top:5px;
							margin-bottom: 15px;
							background: #cccccc;"
			/>
			
			<table>
				<tr>
					<th>Appointment ID</th>
					<th>Patient ID</th>
					<th>Doctor ID</th>
					<th>Date</th>
					<th>Room</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var = "tempAppointment" items = "${APPOINTMENT_LIST}">
				
					<!-- set up a link for each appointmnet -->
					<c:url var = "tempLink" value = "AppointmentControllerServlet">
						<c:param name = "command" value = "LOAD"/>
						<c:param name = "appointmentId" value = "${tempAppointment.appointmentId}"/>
					</c:url>
					
					<!-- set up a link for delete appointmnet -->
					<c:url var = "deleteLink" value = "AppointmentControllerServlet">
						<c:param name = "command" value = "DELETE"/>
						<c:param name = "appointmentId" value = "${tempAppointment.appointmentId}"/>
					</c:url>
					
					<tr>
						<td>${tempAppointment.appointmentId}</td>
						<td>${tempAppointment.patientId}</td>
						<td>${tempAppointment.doctorId}</td>
						<td>${tempAppointment.date}</td>
						<td>${tempAppointment.room}</td>
						<td>
							<a href = "${tempLink}">Update</a>
							|
							<a href = "${deleteLink}"
							onclick = "if(!(confirm('Are you sure you want to delete this appointment?'))) return false">
							Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	
	</div>
</body>
</html>

