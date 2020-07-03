package com.oop.login;

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

import com.oop.pharmacist.Pharmacist;
import com.oop.pharmacist.PharmacistDbUtil;

/**
 * Servlet implementation class RegisterControllerServlet
 */
@WebServlet("/RegisterControllerServlet")
public class RegisterControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private RegisterDbUtil registerDbutil;

	@Resource(name="jdbc/Hospital")
	private DataSource dataSource;
	
	
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		//create our pharmacist db util.....and pass in the connn pool
		try
		{
			registerDbutil=new RegisterDbUtil (dataSource);
		}catch(Exception e)
		{
			throw new ServletException(e);
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			//read the "command" parameter
			String theCommand=request.getParameter("command");
			
		
			//route to the appropriate method
			switch(theCommand)
			{
		
			case "ADD":
				addUser(request,response);
				break;
				

			
			}
		
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw new ServletException(e);
	}
	
}

	private void addUser(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//get form details
		String id=request.getParameter("de_eid");
		String email=request.getParameter("email");
		String pass=request.getParameter("psw");
		String confpass=request.getParameter("pswrepeat");
		String type=request.getParameter("is");
		
		//create new user object
		User theUser = new User(email,pass,confpass,type,id);
		
		//add user to dbutil
		registerDbutil.addUser(theUser);
		
		
		//send to the login page
		RequestDispatcher dispatcher=request.getRequestDispatcher("/RegisterSuccessful.jsp");
		dispatcher.forward(request, response);
		
	}

	

		
		
	

}


