package com.oop.labassistant;



public abstract class Employee {
	
	private int id;
	private String eid;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String conpass;
	private String mobileNo;
	private String address;
	protected double basicSal;
	private boolean user;
	
	
	
	public Employee(int id, String eid, String firstname, String lastname, String email, String password,
			String conpass, String mobileNo, String address, double basicSal,boolean user) {
		super();
		this.id = id;
		this.eid = eid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.conpass = conpass;
		this.mobileNo = mobileNo;
		this.address = address;
		this.basicSal = basicSal;
		this.user = user;
		
	}

	
	public Employee(String firstname, String lastname, String email, String mobileNo, String address, double basicSal, boolean user) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
		this.basicSal = basicSal;
		this.user = user;
	}



	public Employee(String eid, String firstname, String lastname, String email, String mobileNo, String address) {
		super();
		this.eid = eid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
	}



	public Employee(int id, String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
		this.basicSal = basicSal;
	}



	public Employee(int id, String eid, String firstname, String lastname, String email, String mobileNo,
			String address, double basicSal, boolean user) {
		super();
		
		this.id = id;
		this.eid = eid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
		this.basicSal = basicSal;
		this.user = user;
	}
	
	

	public Employee(String eid, String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal, boolean user) {
		super();
		this.eid = eid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
		this.basicSal = basicSal;
		this.user = user;
	}
	
	public Employee(String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
		this.basicSal = basicSal;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEid() {
		return eid;
	}


	public void setEid(String eid) {
		this.eid = eid;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConpass() {
		return conpass;
	}


	public void setConpass(String conpass) {
		this.conpass = conpass;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public double getBasicSal() {
		return basicSal;
	}


	public void setBasicSal(double basicSal) {
		this.basicSal = basicSal;
	}


	public boolean isUser() {
		return user;
	}


	public void setUser(boolean user) {
		this.user = user;
	}
	
	
	
	

}
