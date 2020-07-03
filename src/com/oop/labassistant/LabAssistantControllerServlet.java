package com.oop.labassistant;

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
 * Servlet implementation class LabAssistantControllerServlet
 */
@WebServlet("/LabAssistantControllerServlet")
public class LabAssistantControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LabAssistantDbUtil labassistantDbUtil;
	
	@Resource(name="jdbc/Hospital")
	private DataSource dataSource;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		//create our Lab Assistant db util and pass in the conn pool / datasource
		try {
			labassistantDbUtil = new LabAssistantDbUtil(dataSource);
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
	
			//if the command is missing, then default to listing lab assistants
			if(thecommand == null)
			{
				thecommand = "LIST";
			}
			
			 //route to the appropriate method
			switch (thecommand)
			{
			case "LIST":
				listLabAssistants(request,response);
				break;
				
			case "ADD":
				addLabAssistant(request,response);
				break;
			
			case "LOAD":
				 loadLabAssistant(request,response);
				 break;
				 
			case "UPDATE":
				 updateLabAssistant(request,response);
			     break;
			   
			case "DELETE":
				 deleteLabAssistant(request,response);
			     break;
			     
			     
				
			default:
				listLabAssistants(request,response);
			
			
			}
	
		     //list the lab assistants in MVC
	         listLabAssistants(request,response);
		}
		catch(Exception e)
		{
			throw new ServletException(e);
		}
	}



	private void deleteLabAssistant(HttpServletRequest request, HttpServletResponse response) 
	    throws Exception{
		
		//read lab assistant id from form data
		String theLabAssistantId = request.getParameter("labAssistant_labeid");
		
		//delete lab assistant  from database
		labassistantDbUtil.deleteLabAssistant(theLabAssistantId);
		
		//send them back to "list lab assistant" page
		listLabAssistants(request,response);
	}



	private void updateLabAssistant(HttpServletRequest request, HttpServletResponse response) 
	   throws Exception{
		
		//read lab assistant info from form data
		//String eid=request.getParameter("labAssistant_eid");
		String id=request.getParameter("labAssistant_labeid");
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String phone=request.getParameter("monum");
		Double salary=Double.parseDouble(request.getParameter("salary"));
		Boolean user=Boolean.parseBoolean(request.getParameter("user"));
		
		//create a new lab assistant object
		LabAssistant theLabAss = new LabAssistant(firstname,lastname,email,phone,address,salary,user,id);
			
		//perform update on database
		labassistantDbUtil.updateLabAssistant(theLabAss);
		
		//send them back to the "list lab assistants" page
		listLabAssistants(request,response);
	}



	private void loadLabAssistant(HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		
		//read lab assistant id from form data
		String theLabAssistantId = request.getParameter("labAssistant_labeid");
		
		//get lab assistant from database (db util)
		LabAssistant theLabAssistant = labassistantDbUtil.getLabAssistant(theLabAssistantId);
		
		//place lab assistant in the request attributue
		request.setAttribute("THE_LAB_ASSISTANT",theLabAssistant );
		
		//send to jsp page: update-lab-assistant-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-lab-assistant-form.jsp");
		dispatcher.forward(request,response);
	}



	private void addLabAssistant(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read lab assistants info from form data
		Integer id=Integer.parseInt(request.getParameter("id"));
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String phone=request.getParameter("monum");
		Double salary =Double.parseDouble(request.getParameter("salary"));
		
		//create a new lab assistant object
	    LabAssistant theLabAssistant = new LabAssistant(id,firstname,lastname,email,address,phone,salary);
	    
		//add the lab assistant to the database
	    labassistantDbUtil.addLabAssistant(theLabAssistant);
		
		//send back to main page(the lab assistants list)
		listLabAssistants(request,response);
	}



	private void listLabAssistants(HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		
		//get lab assistants from db util
		List<LabAssistant> labAssistants = labassistantDbUtil.getLabAssistant();
		
		//add lab assistants to the request
		request.setAttribute("LabAssistant_List", labAssistants);
		
		
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-labAssistants.jsp");
		dispatcher.forward(request, response);
	}

}
