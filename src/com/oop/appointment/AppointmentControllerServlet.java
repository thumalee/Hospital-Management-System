package com.oop.appointment;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class AppointmentControllerServlet
 */
@WebServlet("/AppointmentControllerServlet")
public class AppointmentControllerServlet extends HttpServlet {
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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException  {
		
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
					
				case "ADD" :
					addAppointment(request,response);
					break;
					
				case "LOAD" :
					loadAppointment(request,response);
					break;
					
				case "UPDATE" :
					updateAppointment(request,response);
					break;
					
				case "DELETE" :
					deleteAppointment(request,response);
					break;
					
				default : 
					listAppointment(request,response);
			}
			
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void deleteAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

		//read appointment id from form data
		int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
		
		//delete appointment from database
		appointmentDbUtil.deleteAppointment(appointmentId);
		
		//send back to jsp page
		listAppointment(request,response);
	}



	private void updateAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

		//read appointment info from form data
		int aid = Integer.parseInt(request.getParameter("appointmentId"));
		//String pid = request.getParameter("pid");
		String did = request.getParameter("did");
		String date = request.getParameter("date");
		int room = Integer.parseInt(request.getParameter("room"));
		
		//create new appointment object
		Appointment theAppointment = new Appointment(aid,did,date,room);
		
		//perform update on database
		appointmentDbUtil.updateAppointment(theAppointment);
		
		//send them back to jsp page
		listAppointment(request,response);
	}


	private void loadAppointment(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//read appointment id from form data
		String theAppointmentId = request.getParameter("appointmentId");
		
		//get appointment from database
		Appointment theAppointment = appointmentDbUtil.getAppointments(theAppointmentId);
		
		//place appointment in the request attribute
		request.setAttribute("THE_APPOINTMENT", theAppointment);
		
		//send to jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-appointment-form.jsp");
		dispatcher.forward(request, response);
	}


	private void addAppointment(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//read appointment info from form data
		Integer appId = Integer.parseInt(request.getParameter("aid"));
		String patId = request.getParameter("pid");
		String docId = request.getParameter("did");
		String date = request.getParameter("date");
		Integer room = Integer.parseInt(request.getParameter("room"));
		
		//create a new appointment object
		Appointment theAppointment = new Appointment(appId,patId,docId,date,room);
		
		//add appointment to database
		appointmentDbUtil.addAppointment(theAppointment);
		
		//send back to main page
		listAppointment(request,response);
	}


	private void listAppointment(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {

		//get appointments from db util
		List <Appointment> appointments = appointmentDbUtil.getAppointments();
		
		//add appointments to the request
		request.setAttribute("APPOINTMENT_LIST", appointments);
		
		//send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-appointments.jsp");
		dispatcher.forward(request, response);
		
	}

}
