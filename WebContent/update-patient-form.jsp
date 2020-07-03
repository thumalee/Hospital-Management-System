<!DOCTYPE html>
<html>

<head>
	<title>Update Patient</title>
	
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
	<div id="wrapper">
		<div id="header">
			<h2>Hospital</h2>
		</div>
	</div>
	
	<div id = "container">
		<h3>Update Patient</h3>
		
		<form action = "PatientControllerServlet" method = "GET" data-parsley-validate="">
		
		<input type = "hidden" name = "command" value = "UPDATE"/>
		
		<input type="hidden" name="patient_patid"  value="${THE_PATIENT.patientId}" />
		
		<table>
			<tbody>
					
			<tr>
				<td><label>Name : </label></td>
				<td><input type = "text" name = "name"
					 value="${THE_PATIENT.name}"
					 required data-parsley-pattern="^[a-zA-Z]+$"/></td>
			</tr>
			
			<tr>
				<td><label>Gender : </label></td>
				<td><input type = "text" name = "gender"
					 value="${THE_PATIENT.gender}" required/>
					</td>
			</tr>
			
			<tr>
				<td><label>Age : </label></td>
				<td><input type = "text" name = "age"
					value="${THE_PATIENT.age}"
					required data-parsley-type="digits"/>	</td>
			</tr>
			
			<tr>
				<td><label>Mobile Number : </label></td>
				<td><input type = "text" name = "mob"
					value="${THE_PATIENT.mobileNo}"
					required data-parsley-pattern="^[\d\+\-\.\(\)\/\s]*$"/></td>
			</tr>
			
			<tr>
				<td><label>Email : </label></td>
				<td><input type = "text" name = "email"
					value="${THE_PATIENT.email}"
					required data-parsley-type="email"/>
				</td>
			</tr>
			
			
			<tr>
					<td><label></label></td>
					<td><input type ="submit" value = "Save" class = "save"/></td>
				</tr>

			</tbody>
		</table>
		
		</form><div style = "clear : both ;"></div>
		
			<p>
				<a href = "PatientControllerServlet">Back to list</a>
			</p>
		
	</div>
	
</body>

</html>