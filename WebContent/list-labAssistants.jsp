 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
     <title>Lab Assistant Tracker App</title>
     
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
              <input type="button" value="Add Lab Assistant" onclick="window.location.href='add-lab-assistant-form.jsp';
	           return false;" class="add-patient-button "
              />
              
              <table>
              
                 <tr>
                    <th>Lab Assistant ID</th>
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
                  
                  <c:forEach var="theLabAss" items="${LabAssistant_List}">
                  
                  <!-- set up a link for each lab assistant -->
		          <c:url var="templink" value="LabAssistantControllerServlet">
			          <c:param name="command" value="LOAD"/>
			          <c:param name="labAssistant_labeid" value="${theLabAss.labeid}"/>
                 </c:url>
              
                 <!-- link for delete lab assistant -->
		         <c:url var="deletelink" value="LabAssistantControllerServlet">
			     <c:param name="command" value="DELETE"/>
			     <c:param name="labAssisatnt_labeid" value="${theLabAss.labeid}"/>
			     </c:url>
			     
                       <tr style="${(theLabAss.user != 'true')? 'background-color:#80ccff':''}">
                           <td>${theLabAss.eid}</td>
		                   <td>${theLabAss.labeid}</td>
		                   <td>${theLabAss.firstname}</td>
		                   <td>${theLabAss.lastname}</td>
		                   <td>${theLabAss.email}</td>
		                   <td>${theLabAss.address}</td>
		                   <td>${theLabAss.mobileNo}</td>
		                   <td>${theLabAss.basicSal}</td>
		                   <td>${theLabAss.user}</td>
		                   <td>
		                         <a href="${templink}">Update</a>
		                         |
			                     <a href="${deletelink}"
			                     onclick="if(!(confirm('Are you sure you want to delete this Lab Assistant?'))) return false">
			                     Delete</a>
		                   </td>
                        </tr>
                       
                  </c:forEach>
                  
               
             
              
              </table>
              
          </div>
     </div>

</body>
</html>