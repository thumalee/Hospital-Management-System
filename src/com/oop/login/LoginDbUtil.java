package com.oop.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

public class LoginDbUtil {
	
	
	private DataSource dataSource;

	public LoginDbUtil(DataSource thedataSource) {
		super();
		this.dataSource = thedataSource;
	}
	
	
	
	
	public boolean check(String email,String password)
	{
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet rs=null;
		
		try
		{
			//get db connection
			myConn=dataSource.getConnection();
			
			
			//sql statemtn
			String sql="select email,pass from Usertab where email=? and pass=? ";
			
			
			myStmt=myConn.prepareStatement(sql);
			
			//set the param values for user
			myStmt.setString(1, email);
			myStmt.setString(2, password);
			
			rs=myStmt.executeQuery();
			
			if(rs.next())
			{
				return true;  
			}
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		return false;
	}







	public User gettype(String email) {
		
		User type=null;
		
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet rs=null;
		
		try
		{
			//get db connection
			myConn=dataSource.getConnection();
			
			
			//sql statemtn
			String sql="select type from Usertab where email=?";
			
			
			myStmt=myConn.prepareStatement(sql);
			
			//set the param values for user
			myStmt.setString(1, email);
			
			rs=myStmt.executeQuery();
			
			if(rs.next())
			{
				String t=rs.getString("type");
			
			
			
			type =new User(t);
			
			}
			
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return type;
	}

}
