package com.oop.receptionist;

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
 * Servlet implementation class ReceptionistControllerServlet
 */
@WebServlet("/ReceptionistControllerServlet")
public class ReceptionistControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReceptionistDbUtil receptionistDbUtil;
	
	@Resource(name="jdbc/Hospital")
	private DataSource dataSource;   

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		//create our receptionist db util.. and pass in the datasource
		try
		{
			receptionistDbUtil = new ReceptionistDbUtil(dataSource);
		}catch(Exception e)
		{
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			//read the "command" parameter
			String theCommand =  request.getParameter("command");
			
			//if command is missing, default to listing receptionists
			if(theCommand == null)
			{
				theCommand = "LIST";
			}
			
			//route the appropriate method
			switch(theCommand)
			{
			case "LIST" :
				listReceptionist(request,response);
				break;
				
			case "ADD" :
				addReceptionist(request,response);
				break;
				
			case "LOAD" :
				loadReceptionist(request,response);
				break;
				
			case "UPDATE" :
				updateReceptionist(request,response);
				break;

				
			default :
				listReceptionist(request,response);
			}
			
		}
		catch(Exception e)
		{
			throw new ServletException (e);
		}
		
	}


	private void updateReceptionist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read receptionist info from form data 
		String id=request.getParameter("receptionist_reid");
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		String phone=request.getParameter("mob");
		String address=request.getParameter("address");
		Double salary=Double.parseDouble(request.getParameter("salary"));
		Boolean user = Boolean.parseBoolean(request.getParameter("user"));
		
		//create a new receptionist object
		Receptionist thereceptionist = new Receptionist(firstname,lastname,email,phone,address,salary,user,id);
		
		//perform update on database
		receptionistDbUtil.updateReceptionist(thereceptionist);
		
		//send the back to the list receptionists
		listReceptionist(request,response);
		
	}

	private void loadReceptionist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read receptionist info from form data
		String theReceptionistId = request.getParameter("receptionist_reid");
				
		//get receptionist from db util
		Receptionist therecep =  receptionistDbUtil.getReceptionist(theReceptionistId);
		
		//place receptionist in the request attribute
		request.setAttribute("THE_RECEPTIONIST", therecep);
		
		//send to jsp page - update-receptionist
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-receptionist-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void addReceptionist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read receptionist info from form data
		Integer id=Integer.parseInt(request.getParameter("id"));
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		String phone=request.getParameter("mob");
		String address=request.getParameter("address");		
		Double salary =Double.parseDouble(request.getParameter("salary"));
		
		//create a new receptionist object
		Receptionist theReceptionist = new Receptionist(id,firstname,lastname,email,phone,address,salary);
		
		//add the receptionist to the database
		receptionistDbUtil.addReceptionist(theReceptionist);
		
		//send back to the receptionist list
		listReceptionist(request,response);
	}

	private void listReceptionist(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// get receptionist from db util
		List<Receptionist> receptionists = receptionistDbUtil.getReceptionist();
		
		//add receptionist to request
		request.setAttribute("Receptionist_list", receptionists);
		
		//send to jsp 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-receptionist.jsp");
		dispatcher.forward(request, response);
	}

}
