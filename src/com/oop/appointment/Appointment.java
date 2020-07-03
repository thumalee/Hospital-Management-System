package com.oop.appointment;

public class Appointment {

	int appointmentId;
	String patientId;
	String doctorId;
	String date;
	int room;
	
	public Appointment(int appointmentId, String patientId, String doctorId, String date, int room) {
		super();
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.date = date;
		this.room = room;
	}

	public Appointment(String patientId, String doctorId, String date, int room) {
		super();
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.date = date;
		this.room = room;
	}
	

	public Appointment(int appointmentId, String doctorId, String date, int room) {
		super();
		this.appointmentId = appointmentId;
		this.doctorId = doctorId;
		this.date = date;
		this.room = room;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}
	
	
}
