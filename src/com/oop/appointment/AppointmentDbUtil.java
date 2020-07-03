package com.oop.appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class AppointmentDbUtil {

	private DataSource dataSource;
	
	public AppointmentDbUtil(DataSource theDataSource)
	{
		dataSource = theDataSource;
	}
	
	public List<Appointment> getAppointments() throws Exception{
		
		List<Appointment> appointments = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//get a connection
			myConn = dataSource.getConnection();
			
			//create sql statement
			String sql = "select * from appointment";
			
			myStmt = myConn.createStatement();
			
			//execute query
			myRs = myStmt.executeQuery(sql);
			
			//process result set
			while(myRs.next())
			{
				//retrive data from result set row
				int id = myRs.getInt("appointmentId");
				String patient = myRs.getString("patientId");
				String doctor = myRs.getString("doctorId");
				String date = myRs.getString("date");
				int room = myRs.getInt("room");
				
				//crate new appointment object
				Appointment tempAppointment = new Appointment(id,patient,doctor,date,room);
				
				//add it to the set of appointments
				appointments.add(tempAppointment);
			}
						
			return appointments;
		}
		finally
		{
			//close JDBC objects
			close(myConn,myStmt,myRs);
		}
		
		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try
		{
			if(myRs != null)
			{
				myRs.close();
			}
			
			if(myStmt != null)
			{
				myStmt.close();
			}
			
			if(myConn != null)
			{
				myConn.close();
			}
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
		}
	}

	public void addAppointment(Appointment theAppointment) throws SQLException {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			//get db conection
			myConn = dataSource.getConnection();
			
			//create sql for insert
			String sql = "insert into appointment "
						+ "values(?,?,?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			//set the parameter values for appointment
			myStmt.setInt(1, theAppointment.appointmentId);
			myStmt.setString(2, theAppointment.patientId);
			myStmt.setString(3, theAppointment.doctorId);
			myStmt.setString(4, theAppointment.date);
			myStmt.setInt(5, theAppointment.room);
			
			//execute sql insert
			myStmt.execute();
		}
		finally
		{
			//clean up jdbc objects
			close(myConn,myStmt,null);
		}
	}

	public Appointment getAppointments(String theAppointmentId) throws Exception {

		Appointment theAppointment = null;
		
		Connection myConn =null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int appointmentId;
		
		try {
			//convert appointment id to int
			appointmentId = Integer.parseInt(theAppointmentId);
			
			//get a connection to database
			myConn = dataSource.getConnection();
			
			//create sql to get selected appointment
			String sql = "select patientId,doctorId,date,room from appointment where appointmentId=?";
			
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setString(1, theAppointmentId);
			
			//execute statement
			myRs = myStmt.executeQuery();
			
			//retrieve data from result set row
			if(myRs.next())
			{
				String patId = myRs.getString("patientId");
				String docId = myRs.getString("doctorId");
				String date = myRs.getString("date");
				Integer room = myRs.getInt("room");
				
				theAppointment = new Appointment(appointmentId,patId,docId,date,room);
			}
			else
			{
				throw new Exception("Could not find appointment ID " + appointmentId);
			}
			
			return theAppointment;
		}
		finally {
			//close jdbc objects
			close(myConn,myStmt,myRs);
		}
		
	}

	public void updateAppointment(Appointment theAppointment) throws SQLException {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try
		{
			//get db connection
			myConn = dataSource.getConnection();
			
			//create sql update statement
			String sql = "update appointment "
						+ "set doctorId=?,date=?, room=? "
						+ "where appointmentId=?";
			
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setString(1, theAppointment.getDoctorId());
			myStmt.setString(2, theAppointment.getDate());
			myStmt.setInt(3, theAppointment.getRoom());
			myStmt.setInt(4, theAppointment.getAppointmentId());
			
			//execute sql statement
			myStmt.execute();
		}
		finally
		{
			close(myConn,myStmt,null);
		}
	}

	public void deleteAppointment(int appointmentId) throws SQLException {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			//convert appointment id to int
			//int appointmentID = Integer.parseInt("theAppointmentId");
			
			//get connection to database
			myConn = dataSource.getConnection();
			
			//create sql to delete appointment
			String sql = "delete from appointment where appointmentId =?";
			
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, appointmentId);
			
			//execute sql statement
			myStmt.execute();
		}
		finally
		{
			close(myConn,myStmt,null);
		}
	}
}
