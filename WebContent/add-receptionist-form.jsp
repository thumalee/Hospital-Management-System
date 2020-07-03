<!DOCTYPE html>
<html>

<head>
	<title>Add Receptionist</title>
	
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
		<h3>Add Receptionist</h3>
		
		<form action = "ReceptionistControllerServlet" method = "GET" data-parsley-validate="">
		
		<input type = "hidden" name = "command" value = "ADD"/>
		
		<table>
			<tbody>
			
				<tr>
					<td><label>ID:</label></td>
					<td><input type = "text" name = "id" required data-parsley-type="digits"/></td>
				</tr>
				
				<tr>
					<td><label>First Name:</label></td>
					<td><input type = "text" name = "firstname" required data-parsley-pattern="^[a-zA-Z]+$"/></td>
				</tr>
				
				<tr>
					<td><label>Last Name:</label></td>
					<td><input type = "text" name = "lastname" required data-parsley-pattern="^[a-zA-Z]+$"/></td>
				</tr>
				
				<tr>
					<td><label>Email:</label></td>
					<td><input type = "text" name = "email" required data-parsley-type="email"/></td>
				</tr>
				
				<tr>
					<td><label>Mobile Number:</label></td>
					<td><input type = "text" name = "mob" required data-parsley-pattern="^[\d\+\-\.\(\)\/\s]*$"/></td>
				</tr>
				
				<tr>
					<td><label>Address:</label></td>
					<td><input type = "text" name ="address" required/></td>
				</tr>
								
				<tr>
					<td><label>Salary:</label></td>
					<td><input type = "text" name = "salary" required/></td>
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
				<a href = "ReceptionistControllerServlet">Back to list</a>
			</p>

	</div>

</body>

</html>







