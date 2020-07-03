package com.oop.bill;

public class Bill {

	private String bid;
	private double doc_fee;
	private double lab_fee;
	private double med_fee;
	private double hos_fee;
	private double tot;
	private String reid;
	
	public Bill(String bid, double doc_fee, double lab_fee, double med_fee, double hos_fee, double tot, String reid) {
		super();
		this.bid = bid;
		this.doc_fee = doc_fee;
		this.lab_fee = lab_fee;
		this.med_fee = med_fee;
		this.hos_fee = hos_fee;
		this.tot = tot;
		this.reid = reid;
	}

	public Bill(double doc_fee, double lab_fee, double med_fee, double hos_fee, double tot, String reid) {
		super();
		this.doc_fee = doc_fee;
		this.lab_fee = lab_fee;
		this.med_fee = med_fee;
		this.hos_fee = hos_fee;
		this.tot = tot;
		this.reid = reid;
	}
	
	

	public Bill(String bid, double doc_fee, double lab_fee, double med_fee, double hos_fee, String reid) {
		super();
		this.bid = bid;
		this.doc_fee = doc_fee;
		this.lab_fee = lab_fee;
		this.med_fee = med_fee;
		this.hos_fee = hos_fee;
		this.reid = reid;
	}
	
	





	public Bill(String bid, double doc_fee, double lab_fee, double med_fee, double hos_fee) {
		super();
		this.bid = bid;
		this.doc_fee = doc_fee;
		this.lab_fee = lab_fee;
		this.med_fee = med_fee;
		this.hos_fee = hos_fee;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public double getDoc_fee() {
		return doc_fee;
	}

	public void setDoc_fee(double doc_fee) {
		this.doc_fee = doc_fee;
	}

	public double getLab_fee() {
		return lab_fee;
	}

	public void setLab_fee(double lab_fee) {
		this.lab_fee = lab_fee;
	}

	public double getMed_fee() {
		return med_fee;
	}

	public void setMed_fee(double med_fee) {
		this.med_fee = med_fee;
	}

	public double getHos_fee() {
		return hos_fee;
	}

	public void setHos_fee(double hos_fee) {
		this.hos_fee = hos_fee;
	}

	public double getTot() {
		return tot;
	}

	public void setTot(double tot) {
		this.tot = tot;
	}

	public String getReid() {
		return reid;
	}

	public void setReid(String reid) {
		this.reid = reid;
	}

//	public double calTot()
//	{
//		tot=doc_fee+ lab_fee+med_fee+hos_fee;
//		
//		return tot;
//	}
//	
	
	
	
}
