package com.oop.pharmacist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class PharmacistDbUtil {
	
	private DataSource dataSource;

	public PharmacistDbUtil(DataSource thedataSource) {
		super();
		this.dataSource = thedataSource;
	}
	
	public List<Pharmacist> getPharmacist() throws Exception
	{
		List<Pharmacist> pharmalist=new ArrayList<>();
		
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		try
		{
		//get a connection
		myConn=dataSource.getConnection();
		
		
		//create sql
		String sql="select pid,peid,fname,lname,email,phone,address,salary,isActive from pharmacist  inner join  "
				+ "employee  on pharmacist.peid= employee.eid";
				
		
		myStmt= myConn.createStatement();
		
		//execute query
		myRs=myStmt.executeQuery(sql);
		
		//process result set
		while(myRs.next())
		{
			//retrieve data from result set row
			String eid = myRs.getString("pid");
			String peid=myRs.getString("peid");
			String firstName=myRs.getString("fname"); 
			String lastName=myRs.getString("lname");
			String email=myRs.getString("email");
			String mobileNo=myRs.getString("phone");
			String address=myRs.getString("address");
			double basicSal=myRs.getDouble("salary");
			boolean user=myRs.getBoolean("isActive");
			
			//create new student object
			Pharmacist ph=new Pharmacist(eid,firstName,lastName,email,mobileNo,address,basicSal,user,peid);
		
			//add to list
			pharmalist.add(ph);
		
		
		}
		
		return pharmalist;
	}
		
		finally
		{
			//close jdbc objects
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

	public void addPharmacist(Pharmacist thePharmacist) throws Exception {
		// TODO Auto-generated method stub
		
		Connection myConn=null;
		PreparedStatement myStmt=null;
		
		try
		{	
			//get db
			myConn=dataSource.getConnection();
			//create sql for insert
			String sql="exec createeid ?,?,?,?,?,?,?";
					
			myStmt=myConn.prepareStatement(sql);
		
		
		
		//set the param valyues for pharmacist
		myStmt.setInt(1,thePharmacist.getId());	
		myStmt.setString(2, thePharmacist.getFirstname());
		myStmt.setString(3, thePharmacist.getLastname());
		myStmt.setString(4, thePharmacist.getEmail());
		myStmt.setString(5, thePharmacist.getAddress());
		myStmt.setString(6, thePharmacist.getMobileNo());
		myStmt.setDouble(7, thePharmacist.getBasicSal());
		
	
		
		//esecute sql insert
		myStmt.execute();
		
		}finally {
			//clean up jdbc object
			close(myConn,myStmt,null);
			
		}
		
		
	}

	public Pharmacist getPharmacists(String thePharmacistId) throws Exception{
		// TODO Auto-generated method stub
		Pharmacist thePharmacist=null;
		
		Connection myConn =null;
		PreparedStatement myStmt=null;
		ResultSet myRs=null;
		String pharmacistID;
		
		try
		{
			//convert student id to int
			pharmacistID=thePharmacistId;
			
			
			//get connection to db
			myConn=dataSource.getConnection();
			
			
			//create sql to get selected student
			String sql="select fname,lname,email,phone,address,salary,isActive from pharmacist  inner join  employee  "
					+ "on pharmacist.peid= employee.eid "
					+ "where pharmacist.peid=?";
			
			
			//create prepared statements
			
			myStmt=myConn.prepareStatement(sql);
			//set params
			myStmt.setString(1, pharmacistID);
			
			
			//execute state
			myRs=myStmt.executeQuery();
			
			
			//retrieve data from result set row
			if(myRs.next())
			{
				
				String firstName=myRs.getString("fname"); 
				String lastName=myRs.getString("lname");
				String email=myRs.getString("email");
				String mobileNo=myRs.getString("phone");
				String address=myRs.getString("address");
				double basicSal=myRs.getDouble("salary");
				boolean user=myRs.getBoolean("isActive");
				
				
				thePharmacist=new Pharmacist(firstName,lastName,email,mobileNo,address,basicSal,user,thePharmacistId);
			}
			else
			{
				throw new Exception("Could not find pharmacist id:" +pharmacistID);
			}
			
			
			return thePharmacist;
		}finally
		{
			close(myConn,myStmt,null);
		}
		
		
		
	}

	public void updatePharmacist(Pharmacist thepharmacist) throws Exception {
		// TODO Auto-generated method stub
		Connection myConn =null;
		PreparedStatement myStmt=null;
		
		try
		{
		//get db connection
		myConn=dataSource.getConnection();
		
		//create sql update statemnt
		String sql="exec updateemp ?,?,?,?,?,?,?,?";
				
				
		
		//premare statement
		myStmt=myConn.prepareStatement(sql);
		
		
		//set params
		//myStmt.setString(1,thepharmacist.getEid());	
		
		myStmt.setString(1, thepharmacist.getFirstname());
		myStmt.setString(2, thepharmacist.getLastname());
		myStmt.setString(3, thepharmacist.getEmail());
		myStmt.setString(4, thepharmacist.getAddress());
		myStmt.setString(5, thepharmacist.getMobileNo());
		myStmt.setDouble(6, thepharmacist.getBasicSal());
		myStmt.setBoolean(7, thepharmacist.getUser());
		myStmt.setString(8, thepharmacist.getPeid());
		
		
	
		
		//execute sql statement
		myStmt.executeUpdate();
		
	}finally {
		close(myConn,myStmt,null);
	}
	
	}

	public void deleteStudent(String pharmaid)throws Exception {
		// TODO Auto-generated method stub
		
		Connection myConn =null;
		PreparedStatement myStmt=null;
		
		try
		{
		
			
			//db connection
			myConn=dataSource.getConnection();
			
			
			//create sql to delete student
			
			String sql="delete from employee where eid=?";
			//prepare statement
			myStmt=myConn.prepareStatement(sql);
			
			//set params
			myStmt.setString(1, pharmaid);
			//execute sql statemnt
			myStmt.execute();
			
			
			
		}finally
		{
			//clean up jdbc
			close(myConn,myStmt,null);
			
		}
		
	}
}
