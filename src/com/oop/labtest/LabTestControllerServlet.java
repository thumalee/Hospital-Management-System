package com.oop.labtest;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class LabTestControllerServlet
 */
@WebServlet("/LabTestControllerServlet")
public class LabTestControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private LabTestDbUtil labtestDbUtil;
	
	@Resource(name="jdbc/Hospital")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();
		
		//create our Lab Test db util and pass in the conn pool / datasource
		try {
			labtestDbUtil = new LabTestDbUtil(dataSource);
		}
		catch(Exception e)
		{
			throw new ServletException(e);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			 //read the "command parameter" 
			String thecommand = request.getParameter("command");
	
			//if the command is missing, then default to listing lab tests
			if(thecommand == null)
			{
				thecommand = "LIST";
			}
			
			 //route to the appropriate method
			switch (thecommand)
			{
			case "LIST":
				listLabTests(request,response);
				break;
				
			case "ADD":
				addLabTests(request,response);
				break;
			
			case "LOAD":
				 loadLabTests(request,response);
				 break;
				 
			case "UPDATE":
				 updateLabTests(request,response);
			     break;
			   
			case "DELETE":
				 deleteLabTests(request,response);
			     break;
			     
			     
				
			default:
				listLabTests(request,response);
			
			
			}
	
		     //list the lab tests in MVC
	         listLabTests(request,response);
		}
		catch(Exception e)
		{
			throw new ServletException(e);
		}
	}
	private void deleteLabTests(HttpServletRequest request, HttpServletResponse response)
	 throws Exception{
		//read lab test id from form data
	    String theLabTestId = request.getParameter("labTest_testid");
				
	    //delete lab test  from database
		labtestDbUtil.deleteLabTest(theLabTestId);
				
		//send them back to "list lab test" page
		listLabTests(request,response);
		
	}
	private void updateLabTests(HttpServletRequest request, HttpServletResponse response) 
	   throws Exception{
		        //read lab test info from form data
				String id=request.getParameter("labTest_testid");
				String type=request.getParameter("type");
				String patient=request.getParameter("patient");
				String assistant=request.getParameter("assistant");
				
				//create a new lab test object
				LabTest theLabTest = new LabTest(id,type,patient,assistant);
					
				//perform update on database
				labtestDbUtil.updateLabTest(theLabTest);
				
				//send them back to the "list lab tests" page
				listLabTests(request,response);
		
	}
	private void loadLabTests(HttpServletRequest request, HttpServletResponse response)
	  throws Exception{
		        //read lab test id from form data
				String theLabTestId = request.getParameter("labTest_testid");
				
				//get lab test from database (db util)
				LabTest theLabTest = labtestDbUtil.getLabTest(theLabTestId);
				
				//place lab test in the request attributue
				request.setAttribute("THE_LAB_TEST",theLabTest);
				
				//send to jsp page: update-labTest-form.jsp
				RequestDispatcher dispatcher = request.getRequestDispatcher("/update-labTest-form.jsp");
				dispatcher.forward(request,response);
		
	}
	private void addLabTests(HttpServletRequest request, HttpServletResponse response) 
	 throws Exception{
		        //read lab tests info from form data
		 		String id=request.getParameter("id");
				String type=request.getParameter("type");
				String patient=request.getParameter("patient");
				String assistant=request.getParameter("assistant");
				
				//create a new lab test object
			    LabTest theLabTest = new LabTest(id,type,patient,assistant);
			    
				//add the lab test to the database
			    labtestDbUtil.addLabTest(theLabTest);
				
				//send back to main page(the lab test list)
				listLabTests(request,response);
		
	}
	private void listLabTests(HttpServletRequest request, HttpServletResponse response) 
	  throws Exception{
		
		        //get lab tests from db util
				List<LabTest> labTests = labtestDbUtil.getLabTest();
				
				//add lab tests to the request
				request.setAttribute("LabTest_List", labTests);
				
				
				//send to JSP page (view)
				RequestDispatcher dispatcher = request.getRequestDispatcher("/list-labTest.jsp");
				dispatcher.forward(request, response);
		
	}

}
