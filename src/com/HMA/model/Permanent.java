package com.HMA.model;

public class Permanent extends Employee implements Doctor{
	
	
	private String deid;
	private boolean puser;
	
	
	
	public Permanent(String eid, String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal,boolean user,String deid) {
		super(eid, firstname, lastname, email, mobileNo, address, basicSal,user);
		// TODO Auto-generated constructor stub
		this.deid=deid;
		this.puser=user;
	}
	

	public Permanent(String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal,boolean user,String deid) {
		super(firstname, lastname, email, mobileNo, address, basicSal,user);
		// TODO Auto-generated constructor stub
		this.deid=deid;
		this.puser=user;
	}

	public Permanent(int id, String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal,String deid) {
		super(id, firstname, lastname, email, mobileNo, address, basicSal);
		// TODO Auto-generated constructor stub
		this.deid=deid;
	}
	
	public Permanent(int id, String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal) {
		super(id, firstname, lastname, email, mobileNo, address, basicSal);
		// TODO Auto-generated constructor stub
	}

	
	public boolean getPuser() {
		return puser;
	}


	public void setPuser(boolean puser) {
		this.puser = puser;
	}


	public String getDeid() {
		return deid;
	}

	public void setDeid(String deid) {
		this.deid = deid;
	}

	
}
	
	
	
	
	


