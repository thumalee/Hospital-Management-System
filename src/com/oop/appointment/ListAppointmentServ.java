package com.oop.appointment;

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

import com.oop.appointment.Appointment;
import com.oop.appointment.AppointmentDbUtil;

/**
 * Servlet implementation class ListAppointmentServ
 */
@WebServlet("/ListAppointmentServ")
public class ListAppointmentServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AppointmentDbUtil appointmentDbUtil;
	
	@Resource(name="jdbc/Hospital")
	private DataSource dataSource;
   
    @Override
	public void init() throws ServletException {
		super.init();
		
		//create our appointment db util and pass to data source
		try
		{
			appointmentDbUtil = new AppointmentDbUtil(dataSource);
		}
		catch(Exception exc)
		{
			throw new ServletException(exc);
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//read the command parameter
			String theCommand = request.getParameter("command");
			
			//if the command is missing,default to listing appointments
			if(theCommand == null)
			{
				theCommand = "LIST";
			}
			
			//route the appropriate method
			switch(theCommand)
			{
				case "LIST" :
					listAppointment(request,response);
					break;
				default : 
					listAppointment(request,response);
			}
		}
			catch (Exception e) {
				throw new ServletException(e);
			}
			
	}
		private void listAppointment(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {

			//get appointments from db util
			List <Appointment> appointments = appointmentDbUtil.getAppointments();
			
			//add appointments to the request
			request.setAttribute("APPOINTMENT_LIST", appointments);
			
			//send to jsp page
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view-appointment.jsp");
			dispatcher.forward(request, response);
			
		}

}
