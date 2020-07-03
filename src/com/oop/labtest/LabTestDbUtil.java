package com.oop.labtest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.oop.labassistant.LabAssistant;


public class LabTestDbUtil {
	
	private DataSource dataSource;

	public LabTestDbUtil(DataSource theDataSource) {
		
		super();
		this.dataSource = theDataSource;
	}

	public List<LabTest> getLabTest() throws Exception
	{
        List<LabTest> labTests = new ArrayList<>();
		
		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResult = null;
		
		try {
		     //get a connection
			myConnection = dataSource.getConnection();
		
		    //create sql statement
		    String sql = "select testid,type,patient,assistant from labTest ";
		    
			myStatement = myConnection.createStatement();
		    
		    //execute query
			myResult = myStatement.executeQuery(sql);
			
		    //process result set
			while (myResult.next())
			{
				//retrieve data from result set row
				String testid    = myResult.getString("testid");
				String type      = myResult.getString("type");
				String patient   = myResult.getString("patient"); 
				String assistant = myResult.getString("assistant");
				
				//create new lab test object
				LabTest labTest = new LabTest(testid,type,patient,assistant);
				
				//add it to the list of lab tests
				labTests.add(labTest);
			}
		 	 
			return labTests;
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

	public void addLabTest(LabTest theLabTest) throws Exception{
		
		Connection myConnection = null;
		PreparedStatement myStatement = null;
		
		try {
		//get db connection
	    myConnection = dataSource.getConnection();
			
		//create sql for insert
		String sql = "insert into labTest"
				   + "(testid,type,patient,assistant)"
				   + "values (?,?,?,?)";
		
		myStatement = myConnection.prepareStatement(sql);
		
		//set the parameter values for the lab tests
		myStatement.setString(1, theLabTest.getTestid());	
		myStatement.setString(2, theLabTest.getType());
		myStatement.setString(3, theLabTest.getPatient());
		myStatement.setString(4, theLabTest.getAssistant());
		
		//execute sql insert
		myStatement.execute();
		}
		finally {
		//clean up JDBC objects
			close(myConnection, myStatement, null);
		}
		
	}

	public LabTest getLabTest(String theLabTestId) throws Exception{
		
        LabTest theLabTest = null;
		
		Connection myConnection =null;
		PreparedStatement myStatement=null;
		ResultSet myResult=null;
		//String labtestId;
		
		try 
		{
			//convert lab test id to int
			//labtestId = theLabTestId;
			
			//get connection to database
			myConnection = dataSource.getConnection();
			
			//create sql to get selected lab test
			String sql="select type,patient,assistant from labTest "
					+ "where labTest.testid=?";
			
			//create prepared statement
			myStatement = myConnection.prepareStatement(sql);
			
			//set parameteres
			myStatement.setString(1,theLabTestId);
			
			//execute statement
			myResult=myStatement.executeQuery();
			
			//retrieve data from the result set row
			if(myResult.next())
			{
				String type=myResult.getString("type"); 
				String patient=myResult.getString("patient");
				String assistant=myResult.getString("assistant");
			
				theLabTest = new LabTest(type,patient,assistant);
			}
			else
			{
				throw new Exception("Could not find Lab Test Id:" +theLabTestId);
			}
			
			return theLabTest;
		}
		finally
		{
			close(myConnection,myStatement,null);
		}
	}

	public void updateLabTest(LabTest theLabTest) throws Exception{
		
		Connection myConnection = null;
		PreparedStatement myStatement = null;
		
		try
		{
		//get db connection
		myConnection = dataSource.getConnection();
		
		//create sql update statement
		String sql = "update labTest set type=?,patient=?,assistant=? "
				   + "where testid=?";
	
		//prepare statement
		myStatement = myConnection.prepareStatement(sql);
		
		//set parameteres
		
		myStatement.setString(1, theLabTest.getType());
		myStatement.setString(2, theLabTest.getPatient());
		myStatement.setString(3, theLabTest.getAssistant());
		myStatement.setString(4, theLabTest.getTestid());	
				
		//execute sql statement
		myStatement.execute();
		}
      	finally 
	    {
	  	//clean up JDBC objects
		close(myConnection,myStatement,null);
	    }

		
	}

	public void deleteLabTest(String theLabTestId) throws Exception{

		Connection myConnection = null;
		PreparedStatement myStatement = null;
		
		try {
			
			
			//get connection to database
			myConnection = dataSource.getConnection();
			
			//create sql to delete lab test
			String sql="delete from labTest where testid=?";
			
			//prepare statement
			myStatement = myConnection.prepareStatement(sql);
			
			//set parameters
			myStatement.setString(1, theLabTestId);
			
			//execute sql statement
			myStatement.execute();
			
		}
		finally {
			//clean up JDBC code
			close(myConnection,myStatement,null);
		}
		
	}
		
	}


