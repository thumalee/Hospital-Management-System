package com.oop.labassistant;


public class LabAssistant extends Employee {

	private String labeid;
	//private boolean labuser;

	public LabAssistant(String eid, String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal,boolean user,String labeid) {
		super(eid, firstname, lastname, email, mobileNo, address, basicSal,user);
		this.labeid=labeid;
		}
	

	public LabAssistant(String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal,boolean user,String labeid) {
		super(firstname, lastname, email, mobileNo, address, basicSal,user);
        this.labeid=labeid;
		}

	public LabAssistant(int id, String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal,String labeid) {
		super(id, firstname, lastname, email, mobileNo, address, basicSal);
		this.labeid=labeid;
	}
	
	public LabAssistant(int id, String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal) {
		super(id, firstname, lastname, email, mobileNo, address, basicSal);
	}

	public LabAssistant(String firstname, String lastname, String email, String mobileNo, String address,
			double basicSal,String labeid) {
		super(firstname, lastname, email, mobileNo, address, basicSal);
		this.labeid=labeid;
	}




	public String getLabeid() {
		return labeid;
	}

	public void setLabeid(String labeid) {
		this.labeid = labeid;
	}



	
	
	

	

    
	
	
}

