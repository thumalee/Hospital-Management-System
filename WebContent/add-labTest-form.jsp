<!DOCTYPE html>
<html>

<head>
    <title>Add Lab Test</title>
    
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
         <h3>Add Lab Test</h3>
         
         <form name="myform" action="LabTestControllerServlet" method="GET" data-parsley-validate="">
            
               <input type="hidden" name="command" value="ADD" />
               
               <table>
               
                   <tbody>
                   
                       <tr>
                           <td><label>ID value:</label></td>
				           <td><input type="text" name="id" required data-parsley-type="digits"/></td>
                       </tr>
                       
                       <tr>
					      <td><label>Lab test type:</label></td>
					      <td><select name="type">
					               <option value="t1">Complete Blood Count</option>
					               <option value="t2">Prothrombin Time</option>
					               <option value="t3">Basic Metabolic Panel</option>
					               <option value="t4">Comprehensive Metabolic Panel</option>
					               <option value="t5">Lipid Panel</option>
					               <option value="t6">Liver Panel</option>
					               <option value="t7">Thyroid Stimulating Hormone</option>
					               <option value="t8">Hemoglobin A1C</option>
					               <option value="t9">Urinalysis</option>
					               <option value="t10">Cultures</option>                                    
					      </select></td>
				       </tr>
				       
				       <tr>
				
					      <td><label>Patient ID:</label></td>
				          <td><input type="text" name="patient" required data-parsley-type="digits" /></td>
				       </tr>
				
				       <tr>
					      <td><label>Lab Assistant ID:</label></td>
				          <td><input type="text" name="assistant" /></td>
				       </tr>
				
				     <tr>
				          <td><label></label></td>
				          <td><input type="submit" value="Save" class="save"></td>
				     </tr>
				     
                   </tbody>
                   
               </table>
               
         </form>
         
         <div style="clear: both;"></div>
         
         <p>
             <a href="LabTestControllerServlet">Back to List</a>
         </p>
    </div>


</body>

</html>