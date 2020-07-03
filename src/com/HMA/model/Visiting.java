package com.HMA.model;

public class Visiting extends Employee implements Doctor {
	
	private String deid;
	private boolean vuser;

	
	
	public Visiting(String eid, String firstname, String lastname, String email, String mobileNo,
			String address, double basicSal ,boolean user,String deid) {
		super(eid, firstname, lastname, email, mobileNo, address, basicSal,user);
		// TODO Auto-generated constructor stub
		this.deid=deid;
		this.vuser=user;
	}
	
	public Visiting(String firstname, String lastname, String email, String mobileNo, String address, double basicSal,boolean user,String deid) {
		super(firstname, lastname, email, mobileNo, address, basicSal,user);
		// TODO Auto-generated constructor stub
		this.deid=deid;
		this.vuser=user;
	}



	public Visiting(int id, String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal,String deid) {
		super(id, firstname, lastname, email, mobileNo, address, basicSal);
		// TODO Auto-generated constructor stub
		this.deid=deid;
	}
	
	public Visiting(int id, String firstname, String lastname, String email, String mobileNo, String address,double basicSal) {
		super(id, firstname, lastname, email, mobileNo, address,basicSal);
		// TODO Auto-generated constructor stub
		
	}

	

	public boolean getVuser() {
		return vuser;
	}

	public void setVuser(boolean vuser) {
		this.vuser = vuser;
	}

	public String getDeid() {
		return deid;
	}



	public void setDeid(String deid) {
		this.deid = deid;
	}
	
	
}

	
	