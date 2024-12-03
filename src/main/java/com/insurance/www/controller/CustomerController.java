package com.insurance.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.www.model.Customer;
import com.insurance.www.service.CustomerService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/customer")
public class CustomerController 
{
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/add")
	public Customer addData(@RequestBody Customer cust)
	{
		return customerService.addData(cust);
	}
	
	@GetMapping("/fetch")
	public List<Customer> fetchAll()
	{
		return customerService.fetchAll();
	}
	
	@GetMapping("/getcid/{cid}")
	public Customer getcid(@PathVariable String cid)
	{
		return customerService.getcid(cid);
	}
	
	@GetMapping("/get/{mob}")
	public Customer get(@PathVariable String mob)
	{
		return customerService.get(mob);
	}
	
	@GetMapping("/getByEmail/{email}")
	public Customer getEmail(@PathVariable String email)
	{
		return customerService.getEmail(email);
	}
	
	@PutMapping("/updatemobile/{cid}/{mobile}")
	public Customer updateMobile(@PathVariable String cid,@PathVariable String mobile)
	{
		return customerService.updateMobile(cid,mobile);
	}
	
	@PutMapping("/updateemail/{cid}/{email}")
	public Customer updateEmail(@PathVariable String cid,@PathVariable String email)
	{
		return customerService.updateEmail(cid,email);
	}
}
