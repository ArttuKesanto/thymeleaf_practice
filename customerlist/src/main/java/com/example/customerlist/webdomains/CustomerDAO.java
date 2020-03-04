package com.example.customerlist.webdomains;

import java.util.List;

public interface CustomerDAO {
	public void save(Customer customer);
	
	public Customer findOne(long id); // Etsitään yksi. Ei LIST-rakenne!
	
	public List<Customer> findAll();

}
