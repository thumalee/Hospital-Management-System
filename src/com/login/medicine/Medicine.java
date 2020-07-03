package com.login.medicine;

public class Medicine {
	
	private int id;
	private String name;
	private int amount;
	private double price;
	private String users_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUsers_id() {
		return users_id;
	}
	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}
	
	
	public Medicine(int id, String name, int amount, double price, String users_id) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.users_id = users_id;
	}
	public Medicine(String name, int amount, double price, String users_id) {
		super();
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.users_id = users_id;
	}
	
	
	
	
	
	
	
	
	

}
