 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>

<head>
	<title>Receptionist Tracker App</title>
	
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
	
	<div id = "container">
		<div id = "content">
		
			<!-- put new button : Add Receptionist -->
			<input type = "button" value = "Add Receptionist" 
					onclick = "window.location.href = 'add-receptionist-form.jsp'; return false;"
					class = "add-receptionist-button"
			/>
			<table>
				<tr>
					<th>Receptionist ID</th>
					<th>Employee ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Address</th>
					<th>Phone</th>
					<th>Salary</th>
					<th>User Availability</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var = "theRecep" items = "${Receptionist_list}">		
				
				<!-- set up a link for each receptionist -->
				<c:url var = "templink" value = "ReceptionistControllerServlet">
					<c:param name = "command" value = "LOAD"/>
					<c:param name = "receptionist_reid" value = "${theRecep.reid}"/>
				</c:url>
							
				
					<tr style="${(theRecep.user != 'true')? 'background-color:#80ccff':''}">
						<td>${theRecep.eid}</td>
						<td>${theRecep.reid}</td>
						<td>${theRecep.firstname}</td>
						<td>${theRecep.lastname}</td>
						<td>${theRecep.email}</td>
						<td>${theRecep.address}</td>
						<td>${theRecep.mobileNo}</td>
						
						
						<td>${theRecep.basicSal}</td>
						<td>${theRecep.user}</td>
						<td><a href = "${templink}">Update</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>

</html>