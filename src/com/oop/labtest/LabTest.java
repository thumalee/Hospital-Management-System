package com.oop.labtest;

public class LabTest {
	
	private String testid;
	private String type;
	private String patient;
	private String assistant;
	
	
	public LabTest(String testid, String type, String patient, String assistant) {
		super();
		this.testid = testid;
		this.type = type;
		this.patient = patient;
		this.assistant = assistant;
	}
	
	


	public LabTest(String type, String patient, String assistant) {
		super();
		this.type = type;
		this.patient = patient;
		this.assistant = assistant;
	}




	public String getTestid() {
		return testid;
	}


	public void setTestid(String testid) {
		this.testid = testid;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getPatient() {
		return patient;
	}


	public void setPatient(String patient) {
		this.patient = patient;
	}


	public String getAssistant() {
		return assistant;
	}


	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}

	
    
	
	
	
	
	

}
