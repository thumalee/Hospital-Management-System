package com.oop.patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class PatientDbUtil {

	private DataSource dataSource;
	
	public PatientDbUtil(DataSource theDataSource)
	{
		dataSource = theDataSource;
	}
	
	public List <Patient> getPatient() throws Exception
	{
		List <Patient> patList = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//get a connection
			myConn = dataSource.getConnection();
			
			//create a sql statement
			String sql = "select * from patient";
			
			myStmt = myConn.createStatement();
			
			//execute query
			myRs = myStmt.executeQuery(sql);
			
			//process result set
			while(myRs.next())
			{
				//retrieve data from result set row
				String pid = myRs.getString("patid");
				String name = myRs.getString("name");
				String gender = myRs.getString("gender");
				int age = myRs.getInt("age");
				String mobileNo = myRs.getString("mobileNo");
				String email = myRs.getString("email");
				String billNo = myRs.getString("billNo");
				
				//create new patient object
				Patient pat = new Patient(pid,name,gender,age,mobileNo,email,billNo);
				
				//add to the list of patients
				patList.add(pat);
			}
			return patList;
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
			if(myConn !=null)
			{
				myConn.close();
			}
			if(myStmt !=null)
			{
				myStmt.close();
			}if(myRs !=null)
			{
				myRs.close();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void addPatient(Patient thePatient) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try{	
			//get db connection 
			myConn = dataSource.getConnection();
			
			//create sql query for insert
			String sql = "insert into patient "
						+ "(patid,name,gender,age,mobileNo,email,billNo) "
						+ "values(?,?,?,?,?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			//set parameter values for patient
			myStmt.setString(1, thePatient.getPatientId());
			myStmt.setString(2, thePatient.getName());
			myStmt.setString(3, thePatient.getGender());
			myStmt.setInt(4, thePatient.getAge());
			myStmt.setString(5, thePatient.getMobileNo());
			myStmt.setString(6, thePatient.getEmail());
			myStmt.setString(7, thePatient.getBillNo());
			
			//execute sql statement 
			myStmt.execute();
			
		}
		finally {
			//clean up JDBC objects
			close(myConn,myStmt,null);
		}
			
	}

	public Patient getPatient(String thePatientId) throws Exception {
		
		Patient thePatient = null;
		
		Connection myConn = null;
		PreparedStatement myStmt =null;
		ResultSet myRs = null;
		//String patientID;
		
		try
		{
			//convert patient id
			//patientID = thePatientId;
			
			//get connection db
			myConn = dataSource.getConnection();
			
			//create sql to get selected patients
			String sql = "select name,gender,age,mobileNo,email from patient where patid=?";
					
			//create prepared statements
			myStmt = myConn.prepareStatement(sql);
			
			//set parameters
			myStmt.setString(1,  thePatientId);
			
			//execute state
			myRs = myStmt.executeQuery();
			
			//retrieve data from result set row
			if(myRs.next())
			{
				String name=myRs.getString("name"); 
				String gender=myRs.getString("gender"); 
				Integer age=myRs.getInt("age"); 
				String mobileNo=myRs.getString("mobileNo");
				String email=myRs.getString("email"); 
				
				thePatient = new Patient(thePatientId,name,gender,age,mobileNo,email);
			}
			else
			{
				throw new Exception("Could not find patient ID " + thePatientId);
			}
			
			return thePatient;
		}

		finally {
			//clean up JDBC objects
			close(myConn,myStmt,myRs);
		}
	
	}

	public void updatePatient(Patient thePatient) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			//get db connection
			myConn = dataSource.getConnection();

			//create sql update statement
			String sql = "update patient set name=?,gender=?,age=?,mobileNo=?,email=? where patid=?";
			
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set parameters
			myStmt.setString(1, thePatient.getName());
			myStmt.setString(2, thePatient.getGender());
			myStmt.setInt(3, thePatient.getAge());
			myStmt.setString(4, thePatient.getMobileNo());
			myStmt.setString(5, thePatient.getEmail());
			myStmt.setString(6, thePatient.getPatientId());
			
			//execute statement
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		
		}
		
	}

}
	
