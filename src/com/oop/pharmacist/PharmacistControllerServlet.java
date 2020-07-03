package com.oop.pharmacist;

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
 * Servlet implementation class PharmacistController
 */
@WebServlet("/PharmacistControllerServlet")
public class PharmacistControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private PharmacistDbUtil pharmacistDbUtil;
	
	@Resource(name="jdbc/Hospital")
	private DataSource dataSource;
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		//create our pharmacist db util.....and pass in the connn pool
		try
		{
			pharmacistDbUtil=new PharmacistDbUtil (dataSource);
		}catch(Exception e)
		{
			throw new ServletException(e);
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
				//read the "command" parameter
				String theCommand=request.getParameter("command");
				
				//if the command is missing,then default to listing pharmacists
				if(theCommand==null)
				{
					theCommand="LIST";
				}
				//route to the appropriate method
				switch(theCommand)
				{
				//list the pharmacist
				case"LIST":
					listPharmacist(request,response);
					break;
					
				case "ADD":
					addPharmacist(request,response);
					break;
					
				case "LOAD":
					loadPharmacists(request,response);
					break;
					
				case "UPDATE":
					updatePharmacist(request,response);
					break;
					
				case "DELETE":
					deletePharmacist(request,response);
					break;
					
				default:
					listPharmacist(request,response);
				
				}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
		
	}

	private void deletePharmacist(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		
		//read pharmacist id from form data
		String pharmaid=request.getParameter("pharmacist_peid");
		
		//delete pharmacist from database
		pharmacistDbUtil.deleteStudent(pharmaid);
		
		//send them back to "list pharmacits page"
		listPharmacist(request,response);
	}


	private void updatePharmacist(HttpServletRequest request, HttpServletResponse response)throws Exception {
		// TODO Auto-generated method stub
		
		//read pharmacist infor from form data
		//String eid=request.getParameter("pharmacist_eid");
		String id=request.getParameter("pharmacist_peid");
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String phone=request.getParameter("monum");
		Double salary=Double.parseDouble(request.getParameter("salary"));
		Boolean user=Boolean.parseBoolean(request.getParameter("user"));
		
		
		//creade a new pharmacist object
		Pharmacist thepharmacist= new Pharmacist(firstname,lastname,email,phone,address,salary,user,id);
		
		//perform update on database
		pharmacistDbUtil.updatePharmacist(thepharmacist);
		
		
		//send them back to "lisst pharmacist"
		listPharmacist(request,response);
		 
	}


	private void loadPharmacists(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//read pharmacist id from form data
		String thePharmacistId=request.getParameter("pharmacist_peid");
		//get pharmacist from database(db util)
		Pharmacist thepharma=pharmacistDbUtil.getPharmacists(thePharmacistId);
		
		//plaqce pharmacist in the request attribute
		request.setAttribute("THE_PHARMACIST", thepharma);
		
		
		//send to jsp page:update -pharmacist-form.jsp 
		RequestDispatcher dispatcher=
				request.getRequestDispatcher("/update-pharmacist-form.jsp");
		dispatcher.forward(request,response);
	}



	private void addPharmacist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//read pharmacist info from form data
			Integer id=Integer.parseInt(request.getParameter("id"));
			String firstname=request.getParameter("firstname");
			String lastname=request.getParameter("lastname");
			String email=request.getParameter("email");
			String address=request.getParameter("address");
			String phone=request.getParameter("monum");
			Double salary =Double.parseDouble(request.getParameter("salary"));
			
		
		
		//create a new  pharmacist object
		Pharmacist thePharmacist=new Pharmacist(id,firstname,lastname,email,address,phone,salary);
		
		//add the pharmacist to database
		pharmacistDbUtil.addPharmacist(thePharmacist);
		
		//send back to main page
		listPharmacist(request,response);
		
	}


	private void listPharmacist(HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		
		
		//get pharmacist from dbutil
		
		List<Pharmacist> pharmacist=pharmacistDbUtil.getPharmacist();
		
		
		//add students to request
		request.setAttribute("Pharmacist_list",pharmacist );
		
		
		//send to jsp(view)
		RequestDispatcher dispatcher=request.getRequestDispatcher("/list-pharmacist.jsp");
		dispatcher.forward(request, response);
		
	}

}
