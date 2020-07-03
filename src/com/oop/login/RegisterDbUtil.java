package com.oop.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;



public class RegisterDbUtil {
	
	
	private DataSource dataSource;
	
	public RegisterDbUtil(DataSource thedataSource) {
		super();
		this.dataSource = thedataSource;
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


public void addUser(User theUser) throws Exception {
	// TODO Auto-generated method stub
	
	Connection myConn=null;
	PreparedStatement myStmt=null;
	
	
	try
	{
		//get db connection
		myConn=dataSource.getConnection();
		
		//create sql for insert
		String sql="insert into usertab(email,pass,confpass,type,person_id) "
				+ "values(?, ?, ?, ?, ?)";
		
		
		myStmt=myConn.prepareStatement(sql);
		//set the param values for user
		
		myStmt.setString(1,theUser.getEmail());
		myStmt.setString(2,theUser.getPass());
		myStmt.setString(3,theUser.getConfpass());
		myStmt.setString(4,theUser.getType());
		myStmt.setString(5,theUser.getId());
		
		
		//execute sql statemtn
		myStmt.execute();
		
		
	}finally
	{
		//clean up jdbc object
		close(myConn,myStmt,null);
		
	}
	
	
}



}