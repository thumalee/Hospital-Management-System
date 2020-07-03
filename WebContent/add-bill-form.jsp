<!DOCTYPE html>
<html>

<head>
	<title>Add Bil</title>
	
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
		<h3>Add Bill</h3>
		
		<form action = "BillControllerServlet" method = "GET" data-parsley-validate="">
		
		<input type = "hidden" name = "command" value = "ADD"/>
		
		<table>
			<tbody>
			
			<tr>
				<td><label>ID : </label></td>
				<td><input type = "text" name = "id"
					required data-parsley-type="digits"/></td>
			</tr>
			
			<tr>
				<td><label>Doctor fee : </label></td>
				<td><input type = "text" name = "doc"
					required data-parsley-type="digits"/></td>
			</tr>
			
			<tr>
				<td><label>Lab fee : </label></td>
				<td><input type = "text" name = "lab"
					required data-parsley-type="digits"/></td>
			</tr>
			
			<tr>
				<td><label>Medicine fee : </label></td>
				<td><input type = "text" name = "med"
					required data-parsley-type="digits"/></td>
			</tr>
			
			<tr>
				<td><label>Hospital fee : </label></td>
				<td><input type = "text" name = "hosp"
					required data-parsley-type="digits"/></td>
			</tr>
			
			
			
			<tr>
				<td><label>Receptionist ID : </label></td>
				<td><input type = "text" name = "recpid"
					required /></td>
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
				<a href = "BillControllerServlet">Back to list</a>
			</p>
	</div>
	
</body>
</html>