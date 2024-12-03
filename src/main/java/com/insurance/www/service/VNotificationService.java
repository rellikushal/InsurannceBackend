package com.insurance.www.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.www.model.Customer;
import com.insurance.www.model.VNotificationForEmail;
import com.insurance.www.model.VNotificationForMobile;
import com.insurance.www.repository.CustomerRepository;
import com.insurance.www.repository.VNotificationRepositoryForEmail;
import com.insurance.www.repository.VNotificationRepositoryForMobile;



@Service
public class VNotificationService 
{

	@Autowired
	VNotificationRepositoryForEmail emailRepository;
	
	@Autowired
	VNotificationRepositoryForMobile mobileRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	public VNotificationForEmail addNotification(VNotificationForEmail entity) 
	{
		return emailRepository.save(entity);
	}

	public VNotificationForMobile addNotificationForMobile(VNotificationForMobile entity) 
	{
		return mobileRepository.save(entity);
	}

	public List<VNotificationForMobile> getAllNotificationsForMobile() 
	{
		return mobileRepository.findAll();
	}

	public List<VNotificationForEmail> getAllNotificationsForEmail() 
	{
		return emailRepository.findAll();
	}

	public Optional<VNotificationForMobile> getAllMobileByCustomerid(String customerid) 
	{
		String status = "request";
		return mobileRepository.findByCustomeridAndStatus(customerid,status);
	}

	public Optional<VNotificationForEmail> getAllEmailByCustomerid(String customerid) 
	{
		String status = "request";
		return emailRepository.findByCustomeridAndStatus(customerid,status);
	}
	
	public String updateCustomerForMobile(String customerid,VNotificationForMobile data)
	{
		String status = "request";
		Optional<VNotificationForMobile> mobileData = mobileRepository.findByCustomeridAndStatus(customerid, status);
		
		
		if(mobileData.isPresent())
		{
			String value = data.getStatus();
			
			if(value.equals("approved"))
			{
				Customer customer = customerRepository.findByCustomerid(customerid).get(0);
				//Customer firstCustomer = customer.get(0);
				
				customer.setMobile(data.getMobile());
				customerRepository.save(customer);
			}
			
			if(value.equals("approved")||value.equals("rejected"))
			{
				VNotificationForMobile updatedMobileData =  mobileData.get();
				
				updatedMobileData.setStatus(data.getStatus());
				mobileRepository.save(updatedMobileData);
			}
			return "Details are updated"+data.getStatus();
		}
		else 
		{
			return "Details are not updated";
		}
	}
	
	public String updateCustomerForEmail(String customerid,VNotificationForEmail data)
	{
		String status = "request";
		Optional<VNotificationForEmail> emailData = emailRepository.findByCustomeridAndStatus(customerid, status);
		
		if(emailData.isPresent())
		{
			String value = data.getStatus();
			
			if(value.equals("approved"))
			{
				Customer customer = customerRepository.findByCustomerid(customerid).get(0);
				customer.setEmail(data.getEmail());
				customerRepository.save(customer);
			}
			
			if(value.equals("approved")||value.equals("rejected"))
			{
				VNotificationForEmail updatedEmailData = emailData.get();
				updatedEmailData.setStatus(data.getStatus());
				emailRepository.save(updatedEmailData);
			}
			return "Details are updated"+data.getStatus();
		}
		else
		{
			return "Details are not updated";
		}
	}
}
