<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Visiting Doctor List</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
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


<div class="lout">
<form action="UserLogoutControllerServlet">
<input type="submit" value="Logout">
</form>
</div>



<body>

	<div id = "wrapper">
		<div id ="header">
		<h2 align="center">Hospital</h2>
		</div>
	</div>
	
	<div id ="container">
	<h3>Visiting Doctors</h3>
		<div id ="content">
		
		<input type ="button" value="Add Visiting Doctor" 
			   onclick ="window.location.href ='add-visiting-form.jsp';return false;"
			 class ="add-doc-button"
		/>
		
		<table>
		<tr>
			<th>Doctor ID</th>
			<th>Employee ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Address</th> 
			<th>Phone</th>
			<th>Payment</th>
			<th>User Availability</th>
			<th>Action</th>
		</tr>
		<c:forEach var = "tempVisiting" items ="${Visiting_LIST}">
		`<!-- set up a link to each -->
		<c:url var="templink" value = "VisitingDocControllerServ">
			<c:param name = "command" value="LOAD"/>
			<c:param name = "VisitingEid" value = "${tempVisiting.deid}"/>
		</c:url>
			<tr style="${(tempVisiting.vuser != 'true')? 'background-color:#80ccff':''}">
				<td>${tempVisiting.eid}</td>
				<td>${tempVisiting.deid}</td>
				<td>${tempVisiting.firstname}</td>
				<td>${tempVisiting.lastname}</td>
				<td>${tempVisiting.email}</td>
				<td>${tempVisiting.address}</td>
				<td>${tempVisiting.mobileNo}</td>
				<td>${tempVisiting.basicSal}</td>
				<td>${tempVisiting.vuser}</td>
				<td><a href="${templink}">Update</a></td>
			</tr>
		</c:forEach>
	
		</table>
		</div>
	
	</div>
</body>
</html>