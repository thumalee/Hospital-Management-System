package com.oop.bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class BillDbUtil {

	
	private DataSource dataSource;
	
	public BillDbUtil(DataSource theDataSource)
	{
		dataSource = theDataSource;
	}
	
	public List<Bill> getBill() throws Exception
	{
		List<Bill> billlist=new ArrayList<>();
		
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		try
		{
		//get a connection
		myConn=dataSource.getConnection();
		
		
		//create sql
		String sql = "select * from bill";
				
		myStmt= myConn.createStatement();
		
		//execute query
		myRs=myStmt.executeQuery(sql);
		
		//process result set
		while(myRs.next())
		{
			//retrieve data from result set row
			String bid = myRs.getString("bid");
			double doc = myRs.getDouble("doc_fee");
			double lab = myRs.getDouble("lab_fee");
			double med = myRs.getDouble("med_fee");
			double hos = myRs.getDouble("hos_fee");
			double tot = myRs.getDouble("amount");
			String recep = myRs.getString("recep");
			
			//create new student object
			Bill b = new Bill(bid,doc,lab,med,hos,tot,recep);
			
			//add to list
			billlist.add(b);
		}
		return billlist;
		
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

	public void addBill(Bill theBill) throws Exception {
		Connection myConn=null;
		PreparedStatement myStmt=null;
		
		try
		{	
			//get db
			myConn=dataSource.getConnection();
			
			//create sql for insert
			String sql="exec createbill ?,?,?,?,?,?";
					
			myStmt=myConn.prepareStatement(sql);
		
			//set the param valyues for bill
			myStmt.setString(1,theBill.getBid());
			myStmt.setDouble(2,theBill.getDoc_fee());
			myStmt.setDouble(3,theBill.getLab_fee());
			myStmt.setDouble(4,theBill.getMed_fee());
			myStmt.setDouble(5,theBill.getHos_fee());
			//myStmt.setDouble(6,theBill.getTot());
			myStmt.setString(6,theBill.getReid());
			
			//execute sql insert
			myStmt.execute();
			
			}finally {
				//clean up jdbc object
				close(myConn,myStmt,null);
				
			}
	}

	public Bill getBill(String theBillId) throws Exception{
		
		Bill theBill=null;
		
		Connection myConn =null;
		PreparedStatement myStmt=null;
		ResultSet myRs=null;
		
		try
		{
			myConn=dataSource.getConnection();
			
			String sql="select doc_fee,lab_fee,med_fee,hos_fee,recep from bill where bid=?";
			
			myStmt=myConn.prepareStatement(sql);
			
			myStmt.setString(1, theBillId);
			
			myRs=myStmt.executeQuery();
			
			if(myRs.next())
			{
				Double doc=myRs.getDouble("doc_fee");
				Double lab=myRs.getDouble("lab_fee");
				Double med=myRs.getDouble("med_fee");
				Double hos=myRs.getDouble("hos_fee");
				//Double amount=myRs.getDouble("amount");
				String rec=myRs.getString("recep");
				
				theBill=new Bill(theBillId,doc,lab,med,hos,rec);
				
			}
			else
			{
				throw new Exception("Could not find bill id:" +theBillId);
			}
			
			return theBill;
			
		}finally
		{
			close(myConn,myStmt,null);
			
		}
		
		
	}

	public void updateBill(Bill theBill) throws Exception{
		// TODO Auto-generated method stub
		
		Connection myConn =null;
		PreparedStatement myStmt=null;
		
		try
		{
			myConn=dataSource.getConnection();
			
			String sql="exec updatebill ?,?,?,?,?,?";
			
			myStmt=myConn.prepareStatement(sql);
			
			
			myStmt.setString(1, theBill.getBid());
			myStmt.setDouble(2, theBill.getDoc_fee());
			myStmt.setDouble(3, theBill.getLab_fee());
			myStmt.setDouble(4, theBill.getMed_fee());
			myStmt.setDouble(5, theBill.getHos_fee());
			myStmt.setString(6, theBill.getReid());
			
			myStmt.executeUpdate();
			
		}finally
		{
			close(myConn,myStmt,null);
		}
		
	}
}
