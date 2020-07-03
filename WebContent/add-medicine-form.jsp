<!Doctype html>


<html>
<head>
<title> Add medicine</title>
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
		<h3>Add Medicine</h3>
		
		<form action="MedicineControllerServlet" method="GET"  data-parsley-validate="">
			<input type="hidden" name="command" value="ADD"/>
			
			<table>
				<tbody>
					<tr>
					<td><label>Medicine name:</label></td>
					<td><input type="text" name="medname" required  required data-parsley-pattern="^[a-zA-Z]+$"/></td>
					</tr>
					
					<tr>
					<td><label>Amount:</label></td>
					<td><input type="text" name="amount" required data-parsley-type="digits" /></td>
					</tr>
					
					<tr>
					<td><label>Price(Rs.):</label></td>
					<td><input type="text" name="price" required  data-parsley-type="digits"/></td>
					</tr>
					
					<tr>
					<td><label>Pharmacist id(User who updated):</label></td>
					<td><input type="text" name="ph_id" required /></td>
					</tr>
					
					<tr>
					<td><label></label></td>
					<td><input type="submit" value="save" class="save" /></td>
					</tr>
					
					
				
				</tbody>
			
			
			</table>
			
		
		
		</form>
		<div style="clear:both;"></div>
		<p>
		<a href="MedicineControllerServlet">Back to List</a>
		</p>
		
</div>

</body>


</html>