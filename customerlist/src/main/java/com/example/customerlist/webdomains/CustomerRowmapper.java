package com.example.customerlist.webdomains;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;




public class CustomerRowmapper implements RowMapper<Customer> {
	
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setName(rs.getString("fullName"));
	
		customer.setId(rs.getLong("id"));
		
		return customer;
	}

	

}
