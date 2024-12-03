package com.insurance.www.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.insurance.www.model.CustomerSignup;
import com.insurance.www.model.NotificationForEmail;
import com.insurance.www.model.NotificationForMobile;
import com.insurance.www.model.NotificationHystoryForEmail;
import com.insurance.www.model.NotificationHystoryFormobile;
import com.insurance.www.repository.CustomerSignupRepository;
import com.insurance.www.repository.NotificationForEmailRepository;
import com.insurance.www.repository.NotificationForMobileRepository;
import com.insurance.www.repository.NotificationHystoryForEmailRepository;
import com.insurance.www.repository.NotificationHystoryForMobileRepository;

@Service
public class NotificationControllService 
{
	@Autowired
	NotificationForEmailRepository notificationForEmailRepository;
	
	@Autowired
	NotificationForMobileRepository notificationForMobileRepository;
	
	@Autowired
	CustomerSignupRepository cutCustomerSignupRepository;
	
	
	public List<NotificationForMobile> getAllNotificationsFroMobile ()
	{
		return notificationForMobileRepository.findAll();
	}
	
//	public Optional<NotificationForMobile> getNotificationsFroMobile (String customerId)
//	{
//		return notificationForMobileRepository.findByCustomerId(customerId);
//	}
//	
	public String sendNotificationforMobile( NotificationForMobile notificationForMobile)
	{
		notificationForMobileRepository.save(notificationForMobile);
		return "Details Are Stored";
	}
	
	public List<NotificationForMobile> getByStatusForMobile (String status)
	{
		return notificationForMobileRepository.findByStatus(status);
	}
	

	@Autowired
	NotificationHystoryForEmailRepository notificationHystoryForEmailRepository;
	
	@Autowired
	NotificationHystoryForMobileRepository notificationHystoryForMobileRepository;

	public String updateChangeStatus( String customerId ,  NotificationForMobile data )
	{
		String status = "request";
		Optional<NotificationForMobile> notificationData = notificationForMobileRepository.findByCustomerIdAndStatus(customerId,status);
		if(notificationData.isPresent())
		{
			System.out.println(data.getStatus());
			String value = data.getStatus();
			
			if(value.equals("approved"))
			{
				
			List<CustomerSignup> getExistingSignupDetails = cutCustomerSignupRepository.findByCustomerId(customerId);
			CustomerSignup signupDetailsData = getExistingSignupDetails.get(0);
			
		// Long mobileno = data.getMobileNo();
			signupDetailsData.setMobileno(data.getMobileNo());
			System.out.println("notificationData"+data.getMobileNo());
			cutCustomerSignupRepository.save(signupDetailsData);
			
			}
			if(value.equals("rejected") || value.equals("approved"))
			{
			NotificationForMobile existingOptional = notificationData.get();

			// NotificationHystoryFormobile details = null;
				
				
				
				// details.setCustomerId(existingOptional.getCustomerId());
				// details.setDate(existingOptional.getDate());
				
				// details.setMobileNo(existingOptional.getMobileNo());
				// details.setStatus(existingOptional.getStatus());
						
				existingOptional.setStatus(data.getStatus());
				
				// notificationHystoryForMobileRepository.save(details);
			
			existingOptional.setStatus(data.getStatus());
			
			notificationForMobileRepository.save(existingOptional);
			}
			
			return "Details are updated"+data.getStatus();
		}
		else 
		{
			return "Details are not updated";
		}
		
	}
	
	
	public String sendNotificationForEmail(NotificationForEmail notificationForEmail)
	{
		notificationForEmailRepository.save(notificationForEmail);
		return "Details Are Stored";
	}
	
	public List<NotificationForEmail> getAllNotificationsForEmail ()
	{
		return notificationForEmailRepository.findAll();
	}
	
	public List<NotificationForEmail> getByStatusForEmail (String status)
	{
		return notificationForEmailRepository.findByStatus(status);
	}
	
	public String updateChangeStatusForEmail(String customerId , NotificationForEmail data )
	{
		String status = "request";
		Optional<NotificationForEmail> notificationData = notificationForEmailRepository.findByCustomerIdAndStatus(customerId,status);
		if(notificationData.isPresent())
		{
			String value = data.getStatus();
			if(value.equals("approved"))
			{
				List<CustomerSignup> getExistingSignupDetails = cutCustomerSignupRepository.findByCustomerId(customerId);
				CustomerSignup signupDetailsData = getExistingSignupDetails.get(0);
				
				signupDetailsData.setEmail(data.getEmail());
				cutCustomerSignupRepository.save(signupDetailsData);
			}
			
			if(value.equals("rejected") || value.equals("approved"))
			{
				
			NotificationForEmail existingOptional = notificationData.get();

			// NotificationHystoryForEmail details = null;
				
				
				// details.setEmail(existingOptional.getEmail());
				// details.setCustomerId(existingOptional.getCustomerId());
				// details.setDate(existingOptional.getDate());
				// details.setStatus(existingOptional.getStatus());
						
				existingOptional.setStatus(data.getStatus());
				
				// notificationHystoryForEmailRepository.save(details);
			
			existingOptional.setStatus(data.getStatus());
			
			notificationForEmailRepository.save(existingOptional);
			}
			
			return "Details are updated"+data.getStatus();
		}
		else 
		{
			return "Details are not updated";
		}
		
	}

	public Optional<NotificationForMobile> getAllNotificationsFroMobileByCustomerId(String customerId) {
		String status = "request";
		return notificationForMobileRepository.findByCustomerIdAndStatus(customerId,status);
	}
	


	public Optional<NotificationForEmail> getAllNotificationsForEmailByCustomerId(String customerId) {
		String status = "request";
		return notificationForEmailRepository.findByCustomerIdAndStatus(customerId,status);
	
	}
	
}
