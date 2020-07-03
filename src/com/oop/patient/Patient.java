package com.oop.patient;

public class Patient {
	
	private String patientId;
	private String name;
	private String gender;
	private int age;
	private String mobileNo;
	private String email;
	private String billNo;
	
	
	public Patient(String patientId, String name, String gender, int age, String mobileNo, String email) {
		super();
		this.patientId = patientId;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.mobileNo = mobileNo;
		this.email = email;
	}

	public Patient(String patientId, String name, String gender, int age, String mobileNo, String email,
			String billNo) {
		super();
		this.patientId = patientId;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.mobileNo = mobileNo;
		this.email = email;
		this.billNo = billNo;
	}
	
	public Patient(String name, String gender, int age, String mobileNo, String email, String billNo) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.mobileNo = mobileNo;
		this.email = email;
		this.billNo = billNo;
	}


	public Patient(String name, String gender, int age, String mobileNo, String email) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.mobileNo = mobileNo;
		this.email = email;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	
	
	
}
