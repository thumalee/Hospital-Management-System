package com.oop.receptionist;

public class Receptionist extends Employee {

	private String reid;
		

	public Receptionist(String eid, String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal, boolean user, String reid) {
		super(eid, firstname, lastname, email, mobileNo, address, basicSal, user);
		this.reid = reid;
	}

	
	public Receptionist(String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal, boolean user, String reid) {
		super(firstname, lastname, email, mobileNo, address, basicSal, user);
		this.reid = reid;
	}


	public Receptionist(int id, String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal) {
		super(id, firstname, lastname, email, mobileNo, address, basicSal);
		
	}


	public String getReid() {
		return reid;
	}

	public void setReid(String reid) {
		this.reid = reid;
	}

	
	
}
