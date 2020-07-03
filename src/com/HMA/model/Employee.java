package com.HMA.model;

public abstract class Employee {
	
	private int id;
	private String eid;
	private String firstname;
	private String lastname;
	private String email;
	private String mobileNo;
	private String address;
	protected double basicSal;
	private boolean user;
	
	
	
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
	
	public boolean getUser() {
		return user;
	}

	public void setUser(boolean user) {
		this.user = user;
	}



	@Override
	public String toString() {
		return "Employee [id=" + id + ", eid=" + eid + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", mobileNo=" + mobileNo + ", address=" + address + ", basicSal=" + basicSal
				+ "]";
	}
	
	
	
}
