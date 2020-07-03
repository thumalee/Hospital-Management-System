package com.oop.patient;

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
 * Servlet implementation class PatientControllerServlet
 */
@WebServlet("/PatientControllerServlet")
public class PatientControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PatientDbUtil patientDbUtil;
	
	@Resource(name = "jdbc/Hospital")
	private DataSource dataSource;  
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		//create our patient db util.. and pass in the datasource
		try
		{
			patientDbUtil = new PatientDbUtil(dataSource);
		}
		catch(Exception e)
		{
			throw new ServletException(e);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			//read the "command" parameter
			String theCommand =  request.getParameter("command");
			
			//if command is missing, default to listing patients
			if(theCommand == null)
			{
				theCommand = "LIST";
			}
			
			//route the appropriate method
			switch(theCommand)
			{
			case "LIST" :
				listPatient(request,response);
				break;
				
			case "ADD" :
				addPatient(request,response);
				break;
				
			case "LOAD" :
				loadPatient(request,response);
				break;
			
			case "UPDATE" :
				updatePatient(request,response);
				break;
			
			default :
				listPatient(request,response);
			}
		}
		catch(Exception e)
		{
			throw new ServletException(e);
		}
	}


	private void updatePatient(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//read patient info from form data 
		String id=request.getParameter("patient_patid");
		String name=request.getParameter("name");
		String gender=request.getParameter("gender");
		int age=Integer.parseInt(request.getParameter("age"));
		String mobileNo=request.getParameter("mob");
		String email=request.getParameter("email");
		String billNo=request.getParameter("billNo");
		
		//create new patient object
		Patient thePatient = new Patient(id,name,gender,age,mobileNo,email,billNo);
		
		//perform update on database
		patientDbUtil.updatePatient(thePatient);
		
		//send back to the patient list
		listPatient(request,response);
	}

	private void loadPatient(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read patient info from form data
		String thePatientId = request.getParameter("patient_patid");
						
		//get patient from db util
		Patient thepat =  patientDbUtil.getPatient(thePatientId);
				
		//place patient in the request attribute
		request.setAttribute("THE_PATIENT", thepat);
				
		//send to jsp page - update-patient
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-patient-form.jsp");
			dispatcher.forward(request, response);
	}

	private void addPatient(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read patient info from form data
		String pid=request.getParameter("id");
		String name=request.getParameter("name");
		String gender=request.getParameter("gender");
		Integer age=Integer.parseInt(request.getParameter("age"));
		String mobileNo=request.getParameter("mob");
		String email=request.getParameter("email");
		String billNo=request.getParameter("billNo");
				
		//create a new patient object
		Patient thePatient = new Patient(pid,name,gender,age,mobileNo,email,billNo);
		
		//add the patient to the database
		patientDbUtil.addPatient(thePatient);
		
		//send back to the patient list
		listPatient(request,response);
	}

	private void listPatient(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get patient from db util
		List <Patient> patients = patientDbUtil.getPatient();
		
		//add patient to request
		request.setAttribute("Patient_list", patients);
		
		//send to jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-patient.jsp");
		dispatcher.forward(request, response);
	}



	

}
