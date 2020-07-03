package com.login.medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class MedicineDbUtil {
	
	
	private DataSource dataSource;

	public MedicineDbUtil(DataSource thedataSource) {
		super();
		this.dataSource = thedataSource;
	}

	
	public List<Medicine> getMedicine() throws Exception
	{
		List<Medicine> medicine=new ArrayList<>();
		
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		try
		{
		//get a connection
		myConn = dataSource.getConnection();
		
		//create sql statement
		String sql="select * from  medicine ";
		
		myStmt= myConn.createStatement();
		
		//execute query
		myRs=myStmt.executeQuery(sql);
		
		//process result set
		while(myRs.next())
		{
			
			//retrieve data from result set row
			int id=myRs.getInt("id");
			String name=myRs.getString("name");
			int amount=myRs.getInt("amount");
			double price=myRs.getDouble("price");
			String uid=myRs.getString("users_id");
			
			
			//create new medicine
			Medicine theMedicine=new Medicine(id,name,amount,price,uid);
			
			
			//add it to list of medicine
			medicine.add(theMedicine);
			
			
			
		}
		
		return medicine;
		
		}finally
		{
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


public void addMedicine(Medicine theMed) throws Exception {
	// TODO Auto-generated method stub
	
	Connection myConn=null;
	PreparedStatement myStmt=null;
	
	try
	{
		//db conn
		myConn=dataSource.getConnection();
		
		
		String sql="insert into medicine "
				+ "values(?,?,?,?)";
		
		myStmt=myConn.prepareStatement(sql);
		
		//set the param valyues for medicine
		
				myStmt.setString(1, theMed.getName());
				myStmt.setInt(2, theMed.getAmount());
				myStmt.setDouble(3, theMed.getPrice());
				myStmt.setString(4, theMed.getUsers_id());
				
				
				myStmt.execute();
				
				
				
		
	}finally
	{
		close(myConn,myStmt,null);
		
	}
	
}


public Medicine gotMedicine(String theMedicineId) throws Exception {
	// TODO Auto-generated method stub
	
	Medicine theMedicine=null;

	Connection myConn =null;
	PreparedStatement myStmt=null;
	ResultSet myRs=null;
	int medID;
	
	try
	{
		//convert it to int
		medID=Integer.parseInt(theMedicineId);
		
		
		myConn=dataSource.getConnection();
		
		String sql="select name,amount,price,users_id from medicine where id=?";
		
		//create prepared statements
		
		myStmt=myConn.prepareStatement(sql);
		
		//set params
		myStmt.setInt(1, medID);
		
		//execute state
		myRs=myStmt.executeQuery();
		
		//retrieve data from result set row
		if(myRs.next())
		{
			String name=myRs.getString("name");
			Integer amount=myRs.getInt("amount");
			Double price=myRs.getDouble("price");
			String uid=myRs.getString("users_id");
			
			theMedicine=new Medicine(medID,name,amount,price,uid);
		}
		else
		{

			throw new Exception("Could not find medicneid:" +medID);
		}
		
		return theMedicine;
		
	}finally
	{
		close(myConn,myStmt,null);
		
	}
	
	
}


public void updateMedicine(Medicine theMedicine) throws Exception{
	// TODO Auto-generated method stub
	
	Connection myConn =null;
	PreparedStatement myStmt=null;
	
	try
	{
	//get db connection
	myConn=dataSource.getConnection();
	
	String sql="update medicine "
			+ "set name=?,amount=?,price=?,users_id=? "
			+ "where id=?";
			
	myStmt=myConn.prepareStatement(sql);
	

	myStmt.setString(1,theMedicine.getName());
	myStmt.setInt(2, theMedicine.getAmount());
	myStmt.setDouble(3, theMedicine.getPrice());
	myStmt.setString(4, theMedicine.getUsers_id());
	myStmt.setInt(5, theMedicine.getId());
	
	
	myStmt.executeUpdate();
	
	
	
	}finally
	{
		close(myConn,myStmt,null);
	}
	
}


public void deleteMedicine(int id) throws Exception{
	// TODO Auto-generated method stub
	
	
	Connection myConn =null;
	PreparedStatement myStmt=null;
	try
	{
	//db connection
	myConn=dataSource.getConnection();
	
	
	//create sql to delete medicine
	String sql="delete from medicine where id=?";
	
	//prepare statement
	myStmt=myConn.prepareStatement(sql);
	
	//set params
	myStmt.setInt(1, id);
	//execute sql statemnt
	myStmt.execute();
	
	}finally
	{
		//clean up jdbc
		close(myConn,myStmt,null);
		
	}
}

}