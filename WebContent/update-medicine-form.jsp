<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<Title>Update Medicine</Title>
	
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

<div id="wrapper">
	<div id="header">
	<h2>Hospital</h2>
	</div>
</div>

<div id="container">
		<h3>Update Medicine</h3>


		<form action="MedicineControllerServlet" method="GET"  data-parsley-validate="">
		
		<input type="hidden" name="command" value="UPDATE"/>
		
		<input type="hidden" name="medicineId"  value="${THE_MEDICINE.id}" />
		
		<Table>
			<tbody>
			<tr>
					<tr>
					<td><label>Medicine name:</label></td>
					<td><input type="text" name="medname" value="${THE_MEDICINE.name}" required /></td>
					</tr>
					
					<tr>
					<td><label>Amount:</label></td>
					<td><input type="text" name="amount" value="${THE_MEDICINE.amount}" required data-parsley-type="digits" /></td>
					</tr>
					
					<tr>
					<td><label>Price(Rs.):</label></td>
					<td><input type="text" name="price" value="${THE_MEDICINE.price}" required  /></td>
					</tr>
					
					<tr>
					<td><label>Pharmacist id(User who updated):</label></td>
					<td><input type="text" name="ph_id" value="${THE_MEDICINE.users_id}" required /></td>
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
		<a href="MedicineControllerServlet">Back to list</a>
		</p>

</div>
</body>


</html>