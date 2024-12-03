package com.insurance.www.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.www.model.Customer;
import com.insurance.www.repository.CustomerRepository;

@Service
public class CustomerService 
{
	@Autowired
	CustomerRepository customerRepository;
	
	
	public Customer addData(Customer cust)
	{
		LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateString = date.format(formattedDate);
        // String otp = formattedDateString.substring(formattedDateString.length() - 4);
        cust.setCustomerid("VI"+formattedDateString);
		return customerRepository.save(cust);
	}


	public List<Customer> fetchAll() {
		
		return customerRepository.findAll();
	}
	
	
	public Customer getcid(String cid) {
		
		return customerRepository.findByCustomerid(cid).get(0);
	}


	public Customer get(String mob) {
		
		return customerRepository.findByMobile(mob);
	}
	
	
	public Customer getEmail(String email) {
		
		return customerRepository.findByEmail(email);
	}


	public Customer updateMobile(String cid, String mobile) {
		Customer c = customerRepository.findByCustomerid(cid).get(0);
		c.setMobile(mobile);
		customerRepository.save(c);
		return c;
	}
	
	
	public Customer updateEmail(String cid, String email) {
		Customer c = customerRepository.findByCustomerid(cid).get(0);
		c.setEmail(email);
		customerRepository.save(c);
		return c;
	}
}
