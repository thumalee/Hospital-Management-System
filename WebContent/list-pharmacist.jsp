 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>

<title>Pharmacist tracker app</title>

<link type="text/css" rel="stylesheet" href="css/style.css">

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


<body >
<div id="wrapper">
	<div id="header">
	<h2>Welcome ${email}
			add Pharmacist</h2>
	</div>
</div>

<div id="container">
	<div id="content">
	<!-- button -->
	<input type="button" value="add pharmacist" onclick="window.location.href='add-pharmacist-form.jsp';
	return false;" class="add-patient-button"
	/>
	
	<table >
		<tr>
			<th>pharmacist id</th>
			<th>employee id</th>
			<th>firstname</th>
			<th>lastname</th>
			<th>email</th>
			<th>address</th>
			<th>phone</th>
			<th>salary</th>
			<th>User Availabilty</th>
			<th>Action</th>
		</tr>
		<c:forEach var="thepharmas" items="${Pharmacist_list}"> 
		
		<!-- set up a link for each pharmacist -->
		<c:url var="templink" value="PharmacistControllerServlet">
			<c:param name="command" value="LOAD"/>
			<c:param name="pharmacist_peid" value="${thepharmas.peid}"/>
			
		</c:url>
		
		<tr style="${(thepharmas.user!='true') ? 'background-color: #80ccff' : ''}">
		<td>${thepharmas.eid}</td>
		<td>${thepharmas.peid}</td>
		<td>${thepharmas.firstname}</td>
		<td>${thepharmas.lastname}</td>
		<td>${thepharmas.email}</td>
		<td>${thepharmas.address}</td>
		<td>${thepharmas.mobileNo}</td>
		<td>${thepharmas.basicSal}</td>
		<td>${thepharmas.user}</td>
		<td><a href="${templink}">Update</a>
			|
			
		</td>
		
		</tr>
			
	</c:forEach>
	
	
	</table>
	
	
	
	</div>
	
	</div>

</body>


</html>