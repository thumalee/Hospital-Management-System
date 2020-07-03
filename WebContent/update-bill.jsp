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
		<h3>Update Bill</h3>


		<form action="BillControllerServlet" method="GET" data-parsley-validate="">
		
		<input type="hidden" name="command" value="UPDATE"/>
		
		<input type="hidden" name="bill_id"  value="${THE_BILL.bid}" />
		
		<Table>
			<tbody>
			<tr>
					<tr>
				<td><label>Doctor fee : </label></td>
				<td><input type = "text" name = "doc" value="${THE_BILL.doc_fee}" required data-parsley-type="digits"/></td>
			</tr>
			
			<tr>
				<td><label>Lab fee : </label></td>
				<td><input type = "text" name = "lab" value="${THE_BILL.lab_fee}" required data-parsley-type="digits"/></td>
			</tr>
			
			<tr>
				<td><label>Medicine fee : </label></td>
				<td><input type = "text" name = "med" value="${THE_BILL.med_fee}" required data-parsley-type="digits"/></td>
			</tr>
			
			<tr>
				<td><label>Hospital fee : </label></td>
				<td><input type = "text" name = "hosp" value="${THE_BILL.hos_fee}" required data-parsley-type="digits"/></td>
			</tr>
			
			
			
			<tr>
				<td><label>Receptionist ID : </label></td>
				<td><input type = "text" name = "recpid" value="${THE_BILL.reid}" required /></td>
			</tr>
			
			<tr>
			
			<tr>
					<td><label></label></td>
				<td><input type="submit" value="save"  class="save"/></td>
			
		</tr>
			
			</tbody>
		
		
		
		</Table>
		
		
		
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
		<a href="BillControllerServlet">Back to list</a>
		</p>

</div>
</body>


</html>