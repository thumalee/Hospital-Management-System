<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Permanent Doctor</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-style.css">
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
		<div id ="header">
		<h2 align="center">Hospital</h2>
		</div>
	</div>
	
	<div id ="container">
	
		<h3>Permanent Doctors - Update</h3>
		
		<form name ="myform" action ="PermanentDocControllerServ" method = "GET" data-parsley-validate="">
			
			<input type = "hidden" name ="command" value = "UPDATE"/>
			
			<input type = "hidden" name ="PermanentEid" value = "${THE_PERMANENT.deid}"/>
			
			<table>
				<tbody>
				
				<tr>
				<td><label>First Name:</label></td>
				<td><input type ="text" name = "firstname" value ="${THE_PERMANENT.firstname}" required data-parsley-pattern="^[a-zA-Z]+$"></td>
				</tr>
				<td><label>Last name:</label></td>
				<td><input type="text" name="lastname" value ="${THE_PERMANENT.lastname}" required data-parsley-pattern="^[a-zA-Z]+$"/></td>
				</tr>
				
				<tr>
				<td><label>Email:</label></td>
				<td><input type="text" name="email" value ="${THE_PERMANENT.email}" required data-parsley-type="email" /></td>
				</tr>
				
				<tr>
					<td><label>Address:</label></td>
				<td><input type="text" name="address" value ="${THE_PERMANENT.address}" required/></td>
				</tr>
				
				<tr>
					<td><label>Mobile number:</label></td>
				<td><input type="text" name="monum" value ="${THE_PERMANENT.mobileNo}" required data-parsley-pattern="^[\d\+\-\.\(\)\/\s]*$"/></td>
				</tr>
				
			 	<tr>
					<td><label>Salary:</label></td>
				<td><input type="text" name="salary" value ="${THE_PERMANENT.basicSal}" required data-parsley-type="digits"/></td>
				</tr>
				 
				 <tr>
					<td><label>Availability:</label></td>
				<td><input type="text" name="user" value ="${THE_PERMANENT.puser}" required /></td>
				</tr>
				
				<tr>
					<td><label></label></td>
				<td><input type="submit" value="save"  class="save"/></td>
				</tr>
				</tbody>
			</table>
		
		</form>
		
		<div style = "clear: both;"></div>
		<p>
			<a href="PermanentDocControllerServ">Back To List </a>
		</p>
		
	</div>
</body>
</html>