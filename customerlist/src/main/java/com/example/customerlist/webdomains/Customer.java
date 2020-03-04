package com.example.customerlist.webdomains;

public class Customer {

	private long id;
	private String fullName;
	
	public Customer(long id, String fullName) {
		super();
		this.id = id;
		this.fullName = fullName;
	}
	

	public Customer(String fullName) {
		super();
		this.id = 0;
		this.fullName = fullName;
	}


	public Customer() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return fullName;
	}

	public void setName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return String.format("Customer [id=%d, fullName='%s']", id, fullName);
	}

	
}
