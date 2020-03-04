package com.example.customerlist.webcontrollers;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable; // Ei käytössä nyt.
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.customerlist.webdomains.Customer;
import com.example.customerlist.webdomains.CustomerDAO;


@Controller
public class CustomerController {

	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping(value="/customerlist")
	public String customerlist(Model model) {
		//Fetch all customers
		List<Customer> customers = customerDAO.findAll();
		model.addAttribute("customers", customers);
		return "customerlist";
		
		
	}
	//add new customer
	@RequestMapping(value="/add")
	public String addCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "addacustomer";
	}
	
	//save customer
	
	@RequestMapping(value="/save", method= RequestMethod.POST)
	public String save(Customer customer) {
		customerDAO.save(customer);
		return "redirect:customerlist";
	}
	
	

}
