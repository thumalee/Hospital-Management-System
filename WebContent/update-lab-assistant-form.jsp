<!DOCTYPE html>
<html>

<head>
    <title>Update Lab Assistant</title>
    
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
         <h3>Update Lab Assistant</h3>
         
         <form name="myform"action="LabAssistantControllerServlet" method="GET" >
            
               <input type="hidden" name="command" value="UPDATE" />
               
               <input type="hidden" name="labAssistant_labeid"  value="${THE_LAB_ASSISTANT.labeid}" />
               
               <table>
                   <tbody>
                   
                <tr>
				<td><label>First Name:</label></td>
				<td><input type ="text" name = "firstname" value ="${THE_LAB_ASSISTANT.firstname}" ></td>
				</tr>
				<td><label>Last name:</label></td>
				<td><input type="text" name="lastname" value ="${THE_LAB_ASSISTANT.lastname}" /></td>
				</tr>
				
				<tr>
				<td><label>Email:</label></td>
				<td><input type="text" name="email" value ="${THE_LAB_ASSISTANT.email}" /></td>
				</tr>
				
				<tr>
					<td><label>Address:</label></td>
				<td><input type="text" name="address" value ="${THE_LAB_ASSISTANT.address}"/></td>
				</tr>
				
				<tr>
					<td><label>Mobile number:</label></td>
				<td><input type="text" name="monum" value ="${THE_LAB_ASSISTANT.mobileNo}" /></td>
				</tr>
				
			 	<tr>
					<td><label>Salary:</label></td>
				<td><input type="text" name="salary" value ="${THE_LAB_ASSISTANT.basicSal}" /></td>
				</tr>
				 
				 <tr>
					<td><label>Availability:</label></td>
				<td><input type="text" name="user" value ="${THE_LAB_ASSISTANT.user}"  /></td>
				</tr>
				
				<tr>
					<td><label></label></td>
					<td><input type ="submit" value = "Save" class = "save"/></td>
				</tr>
				
                   </tbody>
               </table>
         </form>
         
         <div style="clear: both;"></div>
         
         <p>
             <a href="LabAssistantControllerServlet">Back to List</a>
         </p>
    </div>


</body>

</html>