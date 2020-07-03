 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <!DOCTYPE html>
<html>

<head>
	<title>Bill Tracker App</title>
	
	<link type = "text/css" rel = "stylesheet" href="css/style.css">
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
		<div id = "header">
			<h2>Welcome  ${email}
			Bill details</h2>
		</div>
	</div>
	
		<input type = "button" value = "Add Patient" 
					onclick = "window.location.href = 'add-patient-form.jsp'; return false;"
					class = "add-patient-button"
			/>
			
			<input type = "button" value = "Add Appointment" 
					onclick = "window.location.href = 'AppointmentControllerServlet'; return false;"
						style="border: 1px solid #666; 
							border-radius: 5px; 
							padding: 4px; 
							font-size: 12px;
							font-weight: bold;
							width: 120px; 
							padding: 5px 10px; 
							margin-top:5px;
							margin-bottom: 15px;
							background: #cccccc;"
			/>
	
	<div id = "container">
		<div id = "content">
		
		<!-- put new button : Add Bill -->
			<input type = "button" value = "Add Bill" 
					onclick = "window.location.href = 'add-bill-form.jsp'; return false;"
					class = "add-patient-button"
			/>
		
		<table>
		
			<tr>
				<th>Bill ID</th>
				<th>Doctor fee</th>
				<th>Lab fee</th>
				<th>Medicine fee</th>
				<th>Hospital fee</th>
				<th>Total</th>
				<th>Receptionist ID</th>
				<th>Action</th>
			</tr>
			
			<c:forEach var = "theBill" items = "${Bill_list}">
			
			<!-- set up a link for each pharmacist -->
		<c:url var="templink" value="BillControllerServlet">
			<c:param name="command" value="LOAD"/>
			<c:param name="bill_id" value="${theBill.bid}"/>
			
		</c:url>
			
			<tr>
				<td>${theBill.bid}</td>
				<td>${theBill.doc_fee}</td>
				<td>${theBill.lab_fee}</td>
				<td>${theBill.med_fee}</td>
				<td>${theBill.hos_fee}</td>
				<td>${theBill.tot}</td>
				<td>${theBill.reid}</td>
				<td><a href="${templink}">Update</a></td>
			</tr>
			</c:forEach>
		</table>
		
		</div>
	</div>
</body>
		
</html>