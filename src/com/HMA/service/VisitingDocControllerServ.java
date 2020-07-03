package com.HMA.service;

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

import com.HMA.model.Visiting;
import com.HMA.util.VisitingDbUtil;

/**
 * Servlet implementation class VisitingDocControllerServ
 */
@WebServlet("/VisitingDocControllerServ")
public class VisitingDocControllerServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private VisitingDbUtil visitingDbUtil;
	
	@Resource(name="jdbc/Hospital")
	
	private DataSource dataSource;
      
	
	
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		//create our visiting db util.....and pass in the conn pool
		try {
			visitingDbUtil =new VisitingDbUtil(dataSource);
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public VisitingDocControllerServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			//read the "command" parameter
			String theCommand = request.getParameter("command");
			
			//if the command is missing,then default to listing vis docs
			if(theCommand == null) {
				theCommand = "LIST";
			}
		
			//route to the appropriate method
			switch(theCommand) {
			
			case "LIST":
				listVisiting(request,response);
				break;
			
			case "ADD":
				addVisiting(request,response);
				break;
			
			case "LOAD":
				loadVisiting(request,response);
				break;
				
			case "UPDATE":
				updateVisiting(request,response);
				break;
				
			default:
				listVisiting(request,response);
			
			}
			
			
			//list in MVC pattern
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
		
		
	}

	private void updateVisiting(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		
		//read the vis info from form data
		String doceid = request.getParameter("VisitingEid");
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String phone=request.getParameter("monum");
		Double salary=Double.parseDouble(request.getParameter("payment"));
		boolean user = Boolean.parseBoolean(request.getParameter("user"));	
		//create new vis obj
		Visiting thevisit = new Visiting(firstname,lastname,email,phone,address,salary,user,doceid);
		
		//perform update on db
		visitingDbUtil.updateVisiting(thevisit);
		
		//send them back to the list
		listVisiting(request,response);
	}

	private void loadVisiting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//read vis ID from form date
		String theVisitingEid = request.getParameter("VisitingEid");
		
		//get vis from db
		Visiting thevisit = visitingDbUtil.getVisiting(theVisitingEid);
		
		//place vis in the request attri
		request.setAttribute("The_VISIT", thevisit);
		
		//send to jsp page: update-visiting-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-visiting-form.jsp");
		dispatcher.forward(request, response);
	}

	private void addVisiting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//read vis doc info from form data
		Integer id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String mobino = request.getParameter("monum");
		Double payment = Double.parseDouble(request.getParameter("payment"));
		
		//create new vis doc obj
		Visiting thevis = new Visiting(id,firstName,lastName,email,mobino,address,payment);
		//add the vis doc to the database
		visitingDbUtil.addVisiting(thevis);
		//send back to main page
		listVisiting(request,response);
	}

	private void listVisiting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//get visiting from db util
		List<Visiting> visiting = visitingDbUtil.getVisiting();
		
		//add vis doc to the req
		request.setAttribute("Visiting_LIST", visiting);
		
		//send JSP page(view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_visiting.jsp");
		dispatcher.forward(request, response );
	
	}
}
