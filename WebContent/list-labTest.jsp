<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
     <title>Lab Test Tracker App</title>
     
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


<form action="UserLogoutControllerServlet">
<input type="submit" value="Logout" style="margin-left:1280px;border: 1px solid #666; 
	border-radius: 5px; 
	padding: 4px; 
	font-size: 12px;
	font-weight: bold;
	width: 120px; 
	padding: 5px 10px; margin-bottom: 5px;
	background: #cccccc;">
</form>


<body>

     <div id="wrapper">
     
          <div id="header">
          
               <h2>Hospital</h2>
               
          </div>
          
     </div>
     
     <div id="container">
     
          <div id="content">
          
              <!-- Add button-->
              <input type="button" value="Add Lab Test" onclick="window.location.href='add-labTest-form.jsp';
	           return false;" class="add-patient-button "
              />
              
              <table>
              
                 <tr>
                    <th>Lab Test ID</th>
                    <th>Test type</th>
                    <th>Patient ID</th>
                    <th>Lab Assistant ID</th>
                    <th>Action</th>
                   
                 </tr>
                  
                  <c:forEach var="theLabTest" items="${LabTest_List}">
                  
                  <!-- set up a link for each lab Test -->
		          <c:url var="templink" value="LabTestControllerServlet">
			          <c:param name="command" value="LOAD"/>
			          <c:param name="labTest_testid" value="${theLabTest.testid}"/>
                 </c:url>
              
                 <!-- link for delete lab test -->
		         <c:url var="deletelink" value="LabTestControllerServlet">
			     <c:param name="command" value="DELETE"/>
			     <c:param name="labTest_testid" value="${theLabTest.testid}"/>
			     </c:url>
			     
                       <tr>
                           <td>${theLabTest.testid}</td>
		                   <td>${theLabTest.type}</td>
		                   <td>${theLabTest.patient}</td>
		                   <td>${theLabTest.assistant}</td>
		                   
		                   <td>
		                         <a href="${templink}">Update</a>
		                         |
			                     <a href="${deletelink}"
			                     onclick="if(!(confirm('Are you sure you want to delete this Lab Test?'))) return false">
			                     Delete</a>
		                   </td>
                        </tr>
                       
                  </c:forEach>
                  
               
             
              
              </table>
              
          </div>
     </div>

</body>
</html>