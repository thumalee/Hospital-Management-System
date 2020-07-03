
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<Title>Update Pharmacist</Title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-style.css">
	
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

<div id="container">
		<h3>Update Pharmacist</h3>


		<form action="PharmacistControllerServlet" method="GET">
		
		<input type="hidden" name="command" value="UPDATE"/>
		
		<input type="hidden" name="pharmacist_peid"  value="${THE_PHARMACIST.peid}" />
		
		<Table>
			<tbody>
				<tr>
					<td><label>First name:</label></td>
				<td><input type="text" name="firstname"
				value="${THE_PHARMACIST.firstname}" required data-parsley-pattern="^[a-zA-Z]+$"/></td>
				</tr>
				<tr>
				
					<td><label>Last name:</label></td>
				<td><input type="text" name="lastname"
				value="${THE_PHARMACIST.lastname}" required data-parsley-pattern="^[a-zA-Z]+$"/></td>
				</tr>
				
				<tr>
					<td><label>email:</label></td>
				<td><input type="text" name="email"
				value="${THE_PHARMACIST.email}" required data-parsley-type="email" /></td>
				</tr>
				
				<tr>
					<td><label>address:</label></td>
				<td><input type="text" name="address"
				value="${THE_PHARMACIST.address}" required/></td>
				</tr>
				
				<tr>
					<td><label>Mobile number:</label></td>
				<td><input type="text" name="monum"
				value="${THE_PHARMACIST.mobileNo}"  required data-parsley-pattern="^[\d\+\-\.\(\)\/\s]*$"/></td>
				</tr>
				
				<tr>
					<td><label>Salary:</label></td>
				<td><input type="text" name="salary"
				value="${THE_PHARMACIST.basicSal}"  required/></td>
				</tr>
				
				<tr>
					<td><label>Availabilty:</label></td>
				<td><input type="text" name="user"
				value="${THE_PHARMACIST.user}" required/></td>
				</tr>
				
				
				<tr>
					<td><label></label></td>
				<td><input type="submit" value="save"  class="save"/></td>
				</tr>
			
			
			</tbody>
		
		
		
		</Table>
		
		
		
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
		<a href="PharmacistControllerServlet">Back to list</a>
		</p>

</div>
</body>


</html>