package com.HMA.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.sql.DataSource;
import com.HMA.model.Permanent;

public class PermanentDbUtil {

	private DataSource dataSource;
	
	public PermanentDbUtil(DataSource theDataSource) {
		
		dataSource = theDataSource;
	}
	
	public List<Permanent> getPermanent() throws Exception{
		
		
		List<Permanent> permanent = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		try{
			
		//get a connection
		myConn = dataSource.getConnection();
		
		//create sql statement
		String sql1 = "select p.dpid,deid,fname,lname,email,phone,address,salary,e.isActive from permanent p,doctor d,employee e "
				+ "where d.did = p.dpid and d.deid = e.eid and d.type ='permanent'";
		
		myStmt = myConn.createStatement();
		//execute query
		myRs = myStmt.executeQuery(sql1);
		//process result set
		while(myRs.next()) {
			
			//retrieve data from result set row
			String eid = myRs.getString("dpid");
			String deid = myRs.getString("deid");
			String fname = myRs.getString("fname");
			String lname = myRs.getString("lname");
			String email = myRs.getString("email");
			String tel = myRs.getString("phone");
			String add = myRs.getString("address");
			double sal = myRs.getDouble("salary");
			boolean user = myRs.getBoolean("isActive");
			//create new doctor object
			Permanent tempEmployee = new Permanent(eid,fname,lname,email,tel,add,sal,user,deid); 
			
			//add it to the list of doctors
			permanent.add(tempEmployee);
		}
		
		
		return permanent;
		}
		finally {
			//close JDBC objects
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

	public void addPermanent(Permanent theper) throws Exception {
		// TODO Auto-generated method stub

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			//get db conn
				myConn = dataSource.getConnection();
			//create sql for insert
				String sql = "exec createdid ?,?,?,?,?,?,?,permanent";
				myStmt=myConn.prepareStatement(sql);
			//set para values for permanent
				myStmt.setInt(1, theper.getId());
				myStmt.setString(2, theper.getFirstname());
				myStmt.setString(3, theper.getLastname());
				myStmt.setString(4, theper.getEmail());
				myStmt.setString(5, theper.getAddress());
				myStmt.setString(6, theper.getMobileNo());
				myStmt.setDouble(7,theper.getBasicSal());
			//execute sql insert
				myStmt.execute();
		}
		finally {
		//clean up JDBC objects
			close(myConn,myStmt,null);
		}
		
	}

	public Permanent getPermanent(String thePermanentEid) throws Exception {
		
		Permanent theperm = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		String PermanentEid;	
		
		try {
			//convert vis id t int
			PermanentEid = thePermanentEid;
			
			//get conn to  db
			myConn = dataSource.getConnection();
			
			//create sql to get selected vis
			String sql ="select d.deid,fname,lname,email,phone,address,salary,isActive from permanent p ,doctor d,employee e where d.deid = e.eid and d.did =p.dpid and d.deid=? ";
			
			//create prepared state
			myStmt = myConn.prepareStatement(sql);
			
			//set te params
			myStmt.setString(1, PermanentEid);
			
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
				
				//use the permId during construction
				theperm = new Permanent(firstName,lastName,email,mobileNo,address,basicSal,user,deid);
			}
			else {
				throw new Exception("Could not find Permanent id:"+PermanentEid);
			}
			
		return theperm;
		}
		finally {
			//clean up JDBC obj
			close(myConn,myStmt,myRs);
		}
	}

	public void updatePermanent(Permanent theperm) throws Exception{
		// TODO Auto-generated method stub
		
		Connection myConn =null;
		PreparedStatement myStmt = null;
		try {
		//get db con
		myConn= dataSource.getConnection();
		
		//create sql update
		String sql = "exec updateempd ?, ?, ?, ?, ?, ?, ?, ?,permanent ";
		
		//prepare statement
		myStmt=myConn.prepareStatement(sql);
		 
		//setprams
		myStmt.setString(1, theperm.getFirstname());
		myStmt.setString(2, theperm.getLastname());
		myStmt.setString(3, theperm.getEmail());
		myStmt.setString(4, theperm.getAddress());
		myStmt.setString(5, theperm.getMobileNo());
		myStmt.setDouble(6, theperm.getBasicSal());
		myStmt.setBoolean(7, theperm.getUser());
		myStmt.setString(8, theperm.getDeid());
		
		
		//execute SQL
		myStmt.execute();
		}
		finally {
	    //clean up JDBC
			close(myConn,myStmt,null);
		}
		
		
	}
		
}

