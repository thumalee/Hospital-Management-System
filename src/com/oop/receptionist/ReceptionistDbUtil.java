package com.oop.receptionist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.oop.receptionist.Receptionist;


public class ReceptionistDbUtil {
	
	private DataSource dataSource;
	
	public ReceptionistDbUtil(DataSource theDataSource)
	{
		dataSource = theDataSource;
	}
	
	public List<Receptionist> getReceptionist() throws Exception
	{
		List <Receptionist> recepList = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try
		{
			//get a connection
			myConn = dataSource.getConnection();
			
			//create sql statement
			String sql = "select rid,reid,fname,lname,email,phone,address,salary,isActive from receptionist inner join employee on "
					+ "receptionist.reid= employee.eid";
			
			myStmt = myConn.createStatement();
			
			//execute query
			myRs = myStmt.executeQuery(sql);
			
			//process result set
			while(myRs.next())
			{
				//retrieve data from result set row
				String eid = myRs.getString("rid");
				String reid=myRs.getString("reid");
				String firstName=myRs.getString("fname"); 
				String lastName=myRs.getString("lname");
				String email=myRs.getString("email");
				String mobileNo=myRs.getString("phone");
				String address=myRs.getString("address");
				double basicSal=myRs.getDouble("salary");
				boolean user = myRs.getBoolean("isActive");
				
				//create new receptionist object
				Receptionist recp = new Receptionist(eid,firstName,lastName,email,mobileNo,address,basicSal,user,reid);
				
				//add to the list of receptionists
				recepList.add(recp);
			}	
			return recepList;
		}
			
		finally
		{
			//close JDBC object	
			close(myConn,myStmt,myRs);
			
		}
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
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

	public void addReceptionist(Receptionist theReceptionist) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try{	
			//get db connection 
			myConn = dataSource.getConnection();
			
			//create sql query for insert
			String sql="exec createreid ?,?,?,?,?,?,?";
			
			myStmt = myConn.prepareStatement(sql);
			
			//set parameter values for receptionist 
			myStmt.setInt(1, theReceptionist.getId());
			myStmt.setString(2, theReceptionist.getFirstname());
			myStmt.setString(3, theReceptionist.getLastname());
			myStmt.setString(4, theReceptionist.getEmail());
			myStmt.setString(5, theReceptionist.getAddress());
			myStmt.setString(6, theReceptionist.getMobileNo());
			myStmt.setDouble(7, theReceptionist.getBasicSal());
			
			//execute sql insert
			myStmt.execute();
			
		}
		finally {
			//clean up JDBC objects
			close(myConn,myStmt,null);
		}
	}

	public Receptionist getReceptionist(String theReceptionistId) throws Exception {
		Receptionist theReceptionist = null;
		
		Connection myConn = null;
		PreparedStatement myStmt =null;
		ResultSet myRs = null;
		String receptionistID;
		
		try {
			//convert receptionist id 
			receptionistID = theReceptionistId;
			
			//get connection to db
			myConn = dataSource.getConnection();
			
			//create sql to get selected receptionist
			String sql="select fname,lname,email,phone,address,salary,isActive from receptionist  inner join  employee  "
					+ "on receptionist.reid= employee.eid "
						+ "where receptionist.reid=?";
			
			//create prepared statements
			myStmt = myConn.prepareStatement(sql);
			
			//set parameters
			myStmt.setString(1, receptionistID);
			
			//execute state
			myRs = myStmt.executeQuery();
			
			//retrieve data from result set row
			if(myRs.next())
			{
				String firstName=myRs.getString("fname"); 
				String lastName=myRs.getString("lname");
				String email=myRs.getString("email");
				String mobileNo=myRs.getString("phone");
				String address=myRs.getString("address");
				double basicSal=myRs.getDouble("salary");
				boolean user = myRs.getBoolean("isActive");
				
				theReceptionist=new Receptionist(firstName,lastName,email,mobileNo,address,basicSal,user,receptionistID);
			}
			
			else
			{
				throw new Exception("Could not find receptionist ID " +receptionistID);
			}
			
			return theReceptionist;
		}
		finally {
			//clean up JDBC objects
			close(myConn,myStmt,myRs);
		}
		
	}

	public void updateReceptionist(Receptionist thereceptionist) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			//get db connection
			myConn = dataSource.getConnection();
			
			//create sql update statement
			String sql="exec updatreemp ?,?,?,?,?,?,?,?";
			
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set parameters
			myStmt.setString(1, thereceptionist.getFirstname());
			myStmt.setString(2, thereceptionist.getLastname());
			myStmt.setString(3, thereceptionist.getEmail());
			myStmt.setString(4, thereceptionist.getMobileNo());
			myStmt.setString(5, thereceptionist.getAddress());
			myStmt.setDouble(6, thereceptionist.getBasicSal());
			myStmt.setBoolean(7, thereceptionist.isUser());
			myStmt.setString(8, thereceptionist.getReid());
		
			
			//execute statement
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		
		}
	}

	

}
