<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Permanent Doctor List</title>
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
	<h3>Permanent Doctors</h3>
		<div id ="content">
		
		<input style = "width:150px;" type ="button" value="Add Permenant Doctor" 
			   onclick ="window.location.href ='add-permanent-form.jsp';return false;"
			 class ="add-doc-button"
		/>
		<table>
		<tr>
			<th>Doctor id</th>
			<th>Employee id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Address</th> 
			<th>Phone</th>
			<th>Salary</th>
			<th>User Availability</th>
			<th>Action</th>
		</tr>
		<c:forEach var = "tempPermanent" items ="${Permanent_LIST}">
		`<!-- set up a link to each -->
		<c:url var="tempLink" value = "PermanentDocControllerServ">
			<c:param name = "command" value="LOAD"/>
			<c:param name = "PermanentEid" value = "${tempPermanent.deid}"/>
		</c:url>
			<tr style="${(tempPermanent.puser!= 'true')? 'background-color:#80ccff':''}">
				<td>${tempPermanent.eid}</td>
				<td>${tempPermanent.deid}</td>
				<td>${tempPermanent.firstname}</td>
				<td>${tempPermanent.lastname}</td>
				<td>${tempPermanent.email}</td>
				<td>${tempPermanent.address}</td>
				<td>${tempPermanent.mobileNo}</td>
				<td>${tempPermanent.basicSal}</td>
				<td>${tempPermanent.puser}</td>
				<td><a href="${tempLink}">Update</a></td>
			</tr>
		</c:forEach>
	
		</table>
		</div>
	
	</div>
</body>
</html>