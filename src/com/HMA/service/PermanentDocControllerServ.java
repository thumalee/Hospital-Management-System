package com.HMA.service;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.HMA.model.Permanent;
import com.HMA.util.PermanentDbUtil;

/**
 * Servlet implementation class PermanentDocControllerServ
 */
@WebServlet("/PermanentDocControllerServ")
public class PermanentDocControllerServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PermanentDbUtil permanentDbUtil;
	@Resource(name="jdbc/Hospital")
	private DataSource dataSource;
	
	
	
	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		//create our permanent db util.....and pass in the conn pool
				try {
					permanentDbUtil =new PermanentDbUtil(dataSource);
				}
				catch(Exception e) {
					throw new ServletException(e);
				}
			}
	
/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//list in MVC pattern
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
				listpermanent(request,response);
				break;
			
			case "ADD":
				addPermanent(request,response);
				break;
			case "LOAD":
				loadPermanent(request,response);
				break;
				
			case "UPDATE":
				updatePermanent(request,response);
				break;
			default:
				listpermanent(request,response);
			}
		}	
			 catch (Exception e) {
					// TODO Auto-generated catch block
					throw new ServletException(e);
				}
		
	}





	private void updatePermanent(HttpServletRequest request, HttpServletResponse response)throws Exception {
	// TODO Auto-generated method stub
	
	//read the perm info from form data
	String doceid = request.getParameter("PermanentEid");
	String firstname=request.getParameter("firstname");
	String lastname=request.getParameter("lastname");
	String email=request.getParameter("email");
	String address=request.getParameter("address");
	String phone=request.getParameter("monum");
	Double salary=Double.parseDouble(request.getParameter("salary"));
	boolean user = Boolean.parseBoolean(request.getParameter("user"));			
	//create new perm obj
	Permanent theperm = new Permanent(firstname,lastname,email,phone,address,salary,user,doceid);
				
	//perform update on db
	permanentDbUtil.updatePermanent(theperm);
			
	//send them back to the list
	listpermanent(request,response);
}

	private void loadPermanent(HttpServletRequest request, HttpServletResponse response)throws Exception {
	// TODO Auto-generated method stub
		//read perm ID from form date
				String thePermanentEid = request.getParameter("PermanentEid");
				
				//get perm from db
				Permanent theperm = permanentDbUtil.getPermanent(thePermanentEid);
				
				//place perm in the request attri
				request.setAttribute("THE_PERMANENT", theperm);
				
				//send to jsp page: update-permanent-form.jsp
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher("/update-permanent-form.jsp");
				dispatcher.forward(request, response);
}

	private void addPermanent(HttpServletRequest request, HttpServletResponse response) throws Exception {
	// TODO Auto-generated method stub
		
		//read per doc info from form data
				Integer id = Integer.parseInt(request.getParameter("pid"));
				String firstName = request.getParameter("pfirstname");
				String lastName = request.getParameter("plastname");
				String email = request.getParameter("pemail");
				String address = request.getParameter("paddress");
				String mobino = request.getParameter("pmonum");
				Double sal = Double.parseDouble(request.getParameter("pSalary"));
				
		//create new per doc obj
				Permanent theper = new Permanent(id,firstName,lastName,email,mobino,address,sal);
				
		//add the per doc to the database
				permanentDbUtil.addPermanent(theper);
		//send back to main page
				listpermanent(request,response);	
}

	private void listpermanent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//get permanent from db util
				List<Permanent> permanent = permanentDbUtil.getPermanent();
				
				//add perm doc to the req
				request.setAttribute("Permanent_LIST", permanent);
				
				//send JSP page(view)
				RequestDispatcher dispatcher = request.getRequestDispatcher("/list_permanent.jsp");
				dispatcher.forward(request, response );
		
	}

}
