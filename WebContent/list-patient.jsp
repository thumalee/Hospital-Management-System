 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <!DOCTYPE html>
<html>

<head>
	<title>Patient Tracker App</title>
	
	<link type = "text/css" rel = "stylesheet" href="css/style.css">
</head>
<%
response.setHeader("Cache-Control","no-cache, no-store,must-revealidate");
if(session.getAttribute("email")==null)
	{
		response.sendRedirect("login.jsp");
	}
	%>


<form action="UserLogoutControllerServlet">
<input type="submit" value="Logout" style="margin-left:1280px;border: 1px solid #666; 
	border-radius: 5px; 
	padding: 4px; 
	font-size: 12px;
	font-weight: bold;
	width: 120px; 
	padding: 5px 10px; margin-bottom: 5px;
	background: #cccccc;">
</form>


<body>
	<div id = "wrapper">
		<div id = "header">
			<h2>Welcome ${email}
			Patient details</h2>
		</div>
	</div>
		<input type = "button" value = "Add Bill" 
					onclick = "window.location.href = 'BillControllerServlet'; return false;"
						class = "add-bill-button"
						
			/>
			
			<input type = "button" value = "Add Appointment" 
					onclick = "window.location.href = 'AppointmentControllerServlet'; return false;"
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

	<div id = "container">
		<div id = "content">
		
		
			<!-- put new button : Add Patient -->
			<input type = "button" value = "Add Patient" 
					onclick = "window.location.href = 'add-patient-form.jsp'; return false;"
					class = "add-patient-button"
			/>
		
			<table>
				<tr>
					<th>Patient ID</th>
					<th>Name</th>
					<th>Gender</th>
					<th>Age</th>
					<th>Phone</th>
					<th>Email</th>
					<th>Bill No</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var = "thePat" items = "${Patient_list}">
				
				<!-- set up a link for each patient -->
				<c:url var = "templink" value = "PatientControllerServlet">
					<c:param name = "command" value = "LOAD"/>
					<c:param name = "patient_patid" value = "${thePat.patientId}"/>
				</c:url>
				
				
				
					<tr>
						<td>${thePat.patientId}</td>
						<td>${thePat.name}</td>
						<td>${thePat.gender}</td>
						<td>${thePat.age}</td>
						<td>${thePat.mobileNo}</td>
						<td>${thePat.email}</td>
						<td>${thePat.billNo}</td>
						<td><a href = "${templink}">Update</a></td>						
						
					</tr>
				</c:forEach>
			
			</table>
 		</div>
	</div>

</body>

</html>