package com.oop.labassistant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;



public class LabAssistantDbUtil {

	private DataSource dataSource;
	
	public LabAssistantDbUtil(DataSource theDataSource) 
	{
		super();
		this.dataSource = theDataSource;
	}
	
	public List<LabAssistant> getLabAssistant() throws Exception
	{
		List<LabAssistant> labAssistants = new ArrayList<>();
		
		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResult = null;
		
		try {
		     //get a connection
			myConnection = dataSource.getConnection();
		
		    //create sql statement
		    String sql = "select labid,labeid,fname,lname,email,phone,address,salary,isActive from labAssistant  inner join  employee  on labAssistant.labeid= employee.eid";
		    
			myStatement = myConnection.createStatement();
		    
		    //execute query
			myResult = myStatement.executeQuery(sql);
			
		    //process result set
			while (myResult.next())
			{
				//retrieve data from result set row
				String eid       = myResult.getString("labid");
				String labeid    = myResult.getString("labeid");
				String firstName = myResult.getString("fname"); 
				String lastName  = myResult.getString("lname");
				String email     = myResult.getString("email");
				String mobileNo  = myResult.getString("phone");
				String address   = myResult.getString("address");
				double basicSal  = myResult.getDouble("salary");
				boolean user     = myResult.getBoolean("isActive");
				
				//create new lab assistant object
				LabAssistant labAss = new LabAssistant(eid,firstName,lastName,email,mobileNo,address,basicSal,user,labeid);
				
				//add it to the list of lab assistants
				labAssistants.add(labAss);
			}
		 	 
			return labAssistants;
		}
		finally 
		{
			//close JDBC objects
			close(myConnection,myStatement,myResult);
		}
		
	}

	private void close(Connection myConnection, Statement myStatement, ResultSet myResult) {
		
		try
		{
			if(myResult != null)
			{
				myResult.close();
			}
			
			if(myStatement != null)
			{
				myStatement.close();
			}
			
			if(myConnection != null)
			{
				myConnection.close();
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void addLabAssistant(LabAssistant theLabAssistant) throws Exception {
		
		Connection myConnection = null;
		PreparedStatement myStatement = null;
		
		try {
		//get db connection
	    myConnection = dataSource.getConnection();
			
		//create sql for insert
		String sql = "exec createleid ?,?,?,?,?,?,?";
		
		myStatement = myConnection.prepareStatement(sql);
		
		//set the parameter values for the lab assistants
		myStatement.setInt(1,theLabAssistant.getId());	
		myStatement.setString(2, theLabAssistant.getFirstname());
		myStatement.setString(3, theLabAssistant.getLastname());
		myStatement.setString(4, theLabAssistant.getEmail());
		myStatement.setString(5, theLabAssistant.getAddress());
		myStatement.setString(6, theLabAssistant.getMobileNo());
		myStatement.setDouble(7, theLabAssistant.getBasicSal());
		
		//execute sql insert
		myStatement.execute();
		}
		finally {
		//clean up JDBC objects
			close(myConnection, myStatement, null);
		}
		
	}

	public LabAssistant getLabAssistant(String theLabAssistantId) throws Exception {
		
		LabAssistant theLabAss = null;
		
		Connection myConnection =null;
		PreparedStatement myStatement=null;
		ResultSet myResult=null;
		//String labassistantId;
		
		try 
		{
			//convert lab assistant id to int
			//labassistantId = theLabAssistantId;
			
			//get connection to database
			myConnection = dataSource.getConnection();
			
			//create sql to get selected lab assistant
			String sql="select fname,lname,email,phone,address,salary,isActive from labAssistant inner join  employee  on labAssistant.labeid= employee.eid "
					+ "where labAssistant.labeid=?";
			
			//create prepared statement
			myStatement = myConnection.prepareStatement(sql);
			
			//set parameteres
			myStatement.setString(1,theLabAssistantId);
			
			//execute statement
			myResult=myStatement.executeQuery();
			
			//retrieve data from the result set row
			if(myResult.next())
			{
				String firstName=myResult.getString("fname"); 
				String lastName=myResult.getString("lname");
				String email=myResult.getString("email");
				String mobileNo=myResult.getString("phone");
				String address=myResult.getString("address");
				double basicSal=myResult.getDouble("salary");
				boolean user=myResult.getBoolean("isActive");
				//String labeid=myResult.getString("labeid");
			
				theLabAss = new LabAssistant(firstName,lastName,email,mobileNo,address,basicSal,user,theLabAssistantId);
			}
			else
			{
				throw new Exception("Could not find Lab Assistant Id:" +theLabAssistantId);
			}
			
			return theLabAss;
		}
		finally
		{
			close(myConnection,myStatement,null);
		}
	}

	public void updateLabAssistant(LabAssistant theLabAss)  throws Exception{
		
		Connection myConnection = null;
		PreparedStatement myStatement = null;
		
		try
		{
		//get db connection
		myConnection = dataSource.getConnection();
		
		//create sql update statement
		String sql = "exec updatelempd ?,?,?,?,?,?,?,?";
	
		//prepare statement
		myStatement = myConnection.prepareStatement(sql);
		
		//set parameteres
		//myStatement.setString(1,theLabAss.getEid());	
		myStatement.setString(1, theLabAss.getFirstname());
		myStatement.setString(2, theLabAss.getLastname());
		myStatement.setString(3, theLabAss.getEmail());
		myStatement.setString(4, theLabAss.getAddress());
		myStatement.setString(5, theLabAss.getMobileNo());
		myStatement.setDouble(6, theLabAss.getBasicSal());
		myStatement.setBoolean(7, theLabAss.isUser());
		myStatement.setString(8, theLabAss.getLabeid());
				
		//execute sql statement
		myStatement.execute();
		}
      	finally 
	    {
	  	//clean up JDBC objects
		close(myConnection,myStatement,null);
	    }

	}

	public void deleteLabAssistant(String theLabAssistantId) throws Exception {
		
		Connection myConnection = null;
		PreparedStatement myStatement = null;
		
		try {
			
			
			//get connection to database
			myConnection = dataSource.getConnection();
			
			//create sql to delete lab assistant
			String sql="delete from employee where eid=?";
			
			//prepare statement
			myStatement = myConnection.prepareStatement(sql);
			
			//set parameters
			myStatement.setString(1, theLabAssistantId);
			
			//execute sql statement
			myStatement.execute();
			
		}
		finally {
			//clean up JDBC code
			close(myConnection,myStatement,null);
		}
		
	}
}
