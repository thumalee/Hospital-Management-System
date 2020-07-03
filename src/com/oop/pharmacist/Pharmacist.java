package com.oop.pharmacist;

public class Pharmacist extends Employee {

	private String peid;

	public Pharmacist(String eid, String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal, boolean user, String peid) {
		super(eid, firstname, lastname, email, mobileNo, address, basicSal, user);
		this.peid = peid;
	}





	public Pharmacist(String firstname, String lastname, String email, String mobileNo, String address, double basicSal,
			boolean user, String peid) {
		super(firstname, lastname, email, mobileNo, address, basicSal, user);
		this.peid = peid;
	}





	public Pharmacist(int id, String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal) {
		super(id, firstname, lastname, email, mobileNo, address, basicSal);
	}





	public String getPeid() {
		return peid;
	}




	public void setPeid(String peid) {
		this.peid = peid;
	}




//	@Override
//	public double calcSal() {
//		// TODO Auto-generated method stub
//		return basicSal;
//	}
//




	
	
	

}
