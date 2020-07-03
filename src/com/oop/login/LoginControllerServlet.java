

package com.oop.login;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.oop.pharmacist.PharmacistDbUtil;

/**
 * Servlet implementation class LoginControllerServlet
 */
@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	private LoginDbUtil loginDbUtil;
	
	@Resource(name="jdbc/Hospital")
	private DataSource dataSource;
	
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		//create our  db util.....and pass in the connn pool
		try
		{
			loginDbUtil=new LoginDbUtil (dataSource);
		}catch(Exception e)
		{
			throw new ServletException(e);
		}
	}
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String email=request.getParameter("email");
		String password=request.getParameter("psw");
		
		
		
		
		if(loginDbUtil.check(email, password))
		{		
			User theUser=loginDbUtil.gettype(email);

			String p="pharmacist";
			String a="admin";
			String r="Receptionist";
			String l="labassistant";
			String d="Doctor";
		
			if(theUser.getType().equals(p))
			{
				HttpSession session=request.getSession();
					session.setAttribute("email", email);
					response.sendRedirect("MedicineControllerServlet");
			}
			else
			if(theUser.getType().equals(a))
			{
				HttpSession session=request.getSession();
				session.setAttribute("email", email);
				response.sendRedirect("admin-dashboard.jsp");
				
			}
			else
			if(theUser.getType().equals(r))
			{
				HttpSession session=request.getSession();
				session.setAttribute("email", email);
				response.sendRedirect("PatientControllerServlet");
					
			}
			else
			if(theUser.getType().equals(l))
			{
					HttpSession session=request.getSession();
					session.setAttribute("email", email);
					response.sendRedirect("LabTestControllerServlet");
						
			}
			else
				if(theUser.getType().equals(d))
				{
						HttpSession session=request.getSession();
						session.setAttribute("email", email);
						response.sendRedirect("ListAppointmentServ");
							
				}
			else
			{
				response.sendRedirect("error.jsp");
			}
				
			
		
	
	}else
	{
		response.sendRedirect("error.jsp");
	}

	}
}


