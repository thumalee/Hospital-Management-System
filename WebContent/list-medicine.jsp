 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!Doctype html>


<html>
<head>
<title> List medicine</title>
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


<body>


<div>
	<div id="wrapper">
		<div id="header">
			Welcome ${email}
			add medicine
				
		</div>
	</div>
			

<div id="container">

	<div id="content">
	<input type="button" value="Add Medicine"
		onclick="window.location.href='add-medicine-form.jsp';return false;"
		class="add-patient-button" />
	<table>
	<tr>
		<th>Name</th>
		<th>Amount</th>
		<th>Price(Rs.)</th>
		<th>Pharmacist id(User who updated)</th>
		<th>Action</th>
		
	</tr>	
	
	<c:forEach var="tempMed"  items="${MEDICINE_LIST}">
	
	<!--  set up a link for every medicine -->
	<c:url var="templink" value="MedicineControllerServlet">
		<c:param name="command" value="LOAD"/>
		<c:param name="medicineId" value="${tempMed.id}"/>
		
		</c:url>
		
		
		<c:url var="deletelink" value="MedicineControllerServlet">
			<c:param name="command" value="DELETE"/>
			<c:param name="medicineId" value="${tempMed.id}"/>
			</c:url>
	<tr>
		<td>${tempMed.name}</td>
		<td>${tempMed.amount}</td>
		<td>${tempMed.price}</td>
		<td>${tempMed.users_id}</td>
		<td><a href="${templink}">Update</a>
		
		<a href="${deletelink}"
			onclick="if(!(confirm('Are you sure you want to delete this pharmacist?'))) return false">
			Delete</a>
		
		</td>
	</tr>
	
	
	</c:forEach>
	</table>
	
	</div>


	
</div>

</div>


</body>


</html>