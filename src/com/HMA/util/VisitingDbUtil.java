package com.HMA.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.sql.DataSource;
import com.HMA.model.Visiting;

public class VisitingDbUtil {
	
	private DataSource dataSource;
	
	public VisitingDbUtil(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}
	
	public List<Visiting> getVisiting() throws Exception{
		
		List<Visiting> visiting = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		try{
			
		//get a connection
		myConn = dataSource.getConnection();
		
		//create sql statement
		String sql = " select v.dvid,deid,fname,lname,email,e.phone,e.address ,e.salary,e.isActive from visiting v,doctor d,employee e \r\n" + 
				"  where d.deid = e.eid and d.did =v.dvid and d.type ='visiting' ";
		
		myStmt = myConn.createStatement();
		//execute query
		myRs = myStmt.executeQuery(sql);
		//process result set
		while(myRs.next()) {
			
			//retrieve data from result set row
			String eid = myRs.getString("dvid");
			String deid = myRs.getString("deid");
			String fname = myRs.getString("fname");
			String lname = myRs.getString("lname");
			String email = myRs.getString("email");
			String tel = myRs.getString("phone");
			String add = myRs.getString("address");
			double sal = myRs.getDouble("salary");
			boolean user = myRs.getBoolean("isActive");
			//create new doctor object
			Visiting tempEmployee1 = new Visiting(eid,fname,lname,email,tel,add,sal,user,deid); 
			
			//add it to the list of doctors
			visiting.add(tempEmployee1);
		}
		
		
		return visiting;
		}
		finally {
			//close JDBC objects
			close(myConn,myStmt,myRs);
			
		}
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs)throws Exception {
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

	public void addVisiting(Visiting thevis) throws Exception {
		// TODO Auto-generated method stub
		
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			//get db conn
			myConn = dataSource.getConnection();
			//create sql for insert
			String sql = "exec createdid ?,?,?,?,?,?,?,visiting";
			myStmt=myConn.prepareStatement(sql);
			//set para values for visiting
			myStmt.setInt(1, thevis.getId());
			myStmt.setString(2, thevis.getFirstname());
			myStmt.setString(3, thevis.getLastname());
			myStmt.setString(4, thevis.getEmail());
			myStmt.setString(5, thevis.getAddress());
			myStmt.setString(6, thevis.getMobileNo());
			myStmt.setDouble(7,thevis.getBasicSal());
			
			//execute sql insert
			myStmt.execute();
		}
		finally {
		//clean up JDBC objects
			close(myConn,myStmt,null);
		}
	}

	public Visiting getVisiting(String theVisitingEid) throws Exception {
		// TODO Auto-generated method stub
		
		Visiting thevisit = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		String VisitingEid;	
		
		try {
			//convert vis id t int
			VisitingEid = theVisitingEid;
			
			//get conn to  db
			myConn = dataSource.getConnection();
			
			//create sql to get selected vis
			String sql ="select d.deid,fname,lname,email,phone,address,salary,isActive from visiting v,doctor d,employee e where d.deid = e.eid and d.did =v.dvid and d.deid=? ";
			
			//create prepared state
			myStmt = myConn.prepareStatement(sql);
			
			//set te params
			myStmt.setString(1, VisitingEid);
			
			//execute statement
			myRs = myStmt.executeQuery();
			
			//retrieve the data from result row
			if(myRs.next()) {
				String firstName=myRs.getString("fname"); 
				String lastName=myRs.getString("lname");
				String email=myRs.getString("email");
				String mobileNo=myRs.getString("phone");
				String address=myRs.getString("address");
				double basicSal=myRs.getDouble("salary");
				String deid=myRs.getString("deid"); 
				boolean user =myRs.getBoolean("isActive");
				
				//use the visitId during construction
				thevisit = new Visiting(firstName,lastName,email,mobileNo,address,basicSal,user,deid);
			}
			else {
				throw new Exception("Could not find Visiting id:"+VisitingEid);
			}
			
		return thevisit;
		}
		finally {
			//clean up JDBC obj
			close(myConn,myStmt,myRs);
		}
	}

	public void updateVisiting(Visiting thevisit) throws Exception {
		// TODO Auto-generated method stub
		Connection myConn =null;
		PreparedStatement myStmt = null;
		try {
		//get db con
		myConn= dataSource.getConnection();
		
		//create sql update
		String sql = "exec updateempd ?, ?, ?, ?, ?, ?, ?, ?,visiting";
		
		//prepare statement
		myStmt=myConn.prepareStatement(sql);
		 
		//setprams
		myStmt.setString(1, thevisit.getFirstname());
		myStmt.setString(2, thevisit.getLastname());
		myStmt.setString(3, thevisit.getEmail());
		myStmt.setString(4, thevisit.getAddress());
		myStmt.setString(5, thevisit.getMobileNo());
		myStmt.setDouble(6, thevisit.getBasicSal());
		myStmt.setBoolean(7, thevisit.getUser());
		myStmt.setString(8, thevisit.getDeid());
		
		//execute SQL
		myStmt.execute();
		}
		finally {
	    //clean up JDBC
			close(myConn,myStmt,null);
		}
		
	}
	
		
	
	

}
