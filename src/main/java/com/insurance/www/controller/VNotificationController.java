package com.insurance.www.controller;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
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
import com.insurance.www.model.VNotificationForEmail;
import com.insurance.www.model.VNotificationForMobile;
import com.insurance.www.repository.CustomerRepository;
import com.insurance.www.service.VNotificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/notification")
@Tag(
		name = "CRUD REST API's for VNotification Controller",
		description = "CRUD rest api in VNotification to CREATE, UPDATE, FETCH and DELETE insurance details."
		)
public class VNotificationController 
{

	@Autowired
	VNotificationService notificationService;
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	
	@Operation(
			summary = "REST api to create VNotificationForMobile",
			description = "Creating the VNotificationForMobile details related to VNotificationForMobile."
			)
	@PostMapping("/addForMobile")
	public VNotificationForMobile addNotificationForMobile(@RequestBody VNotificationForMobile entity) 
	{
		return notificationService.addNotificationForMobile(entity);
	}
	
	
	
	@Operation(
			summary = "REST api to get all VNotificationForMobile Details",
			description = "This api is used to fetch all the vNotificationForMobile available details."
			)
	@GetMapping("/fetchAllMobile")
	public List<VNotificationForMobile> getAllNotificationsForMobile()
	{
		return notificationService.getAllNotificationsForMobile();
	}
	
	
	
	@Operation(
			summary = "REST api to get all VNotificationForMobile Details",
			description = "This api is used to fetch all the VNotificationForMobile available data based on customer id."
			)
	@GetMapping("/getAllMobile/{customerid}")
	public Optional<VNotificationForMobile> getAllMobileByCustomerid(@PathVariable String customerid)
	{
		return notificationService.getAllMobileByCustomerid(customerid);
	}
	
	
	
	@Operation(
			summary = "REST api to update VNotificationForMobile",
			description = "This api is used for updating VNotificationForMobile details for a perticular customer based on customer id."
			)
	@PutMapping("/updateMobile/{customerid}")
	public String updateCustomeForMobile(@PathVariable String customerid,@RequestBody VNotificationForMobile data)
	{
		String status = data.getStatus();
		String emailId = "";
		String mobileNumber = data.getMobile();
		
		sendEmail(customerid,status,emailId,mobileNumber);
		return notificationService.updateCustomerForMobile(customerid, data);
	}
	
	
	
	
	
	@Operation(
			summary = "REST api to create VNotificationForEmail",
			description = "This api is used to create VNotificationForEmail."
			)
	@PostMapping("/addForEmail")
	public VNotificationForEmail addNotification(@RequestBody VNotificationForEmail entity) 
	{
		return notificationService.addNotification(entity);
	}
	
	@GetMapping("/fetchAllEmail")
	public List<VNotificationForEmail> getAllNotificationsForEmail()
	{
		return notificationService.getAllNotificationsForEmail();
	}
	
	@GetMapping("/getAllEmail/{customerid}")
	public Optional<VNotificationForEmail> getAllEmailByCustomerid(@PathVariable String customerid)
	{
		return notificationService.getAllEmailByCustomerid(customerid);
	}
	
	@PutMapping("/updateEmail/{customerid}")
	public String updateCustomerForEmail(@PathVariable String customerid,@RequestBody VNotificationForEmail data)
	{
		String status = data.getStatus();
		String mailId = data.getEmail();
		String mobileNumber = "";
		
		sendEmail(customerid,status,mailId,mobileNumber);
		return notificationService.updateCustomerForEmail(customerid, data);
	}
	
	
	
	
	
	
	
	public String sendEmail(String customerId , String status , String emailId ,String mobileNo)
	{
		
		List<Customer> getExistingCustomerDetails = customerRepository.findByCustomerid(customerId);
		Customer signupDetailsData = getExistingCustomerDetails.get(0);
		String toEmail = signupDetailsData.getEmail();
		
		String postUrl = "https://api.zeptomail.in/v1.1/email";
		StringBuffer sb = new StringBuffer();

		try 
		{
			URL url = new URL(postUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Zoho-enczapikey PHtE6r0EFLjr3jMsp0QAt/+wE8TyN40tr+hmKFMVsIgUXqMFTk0Bqdl6wDPiqU8jXPJHR/ObzN5ttLOe5+ONdGrtZG1NXmqyqK3sx/VYSPOZsbq6x00etFUdcE3aUIbvetFq0ifQvdbcNA==");
		
			JSONObject requestBody = new JSONObject();
			JSONObject from = new JSONObject();
			String email="support@qtnext.com";
			from.put("address", email);
			from.put("name", "RSInsurance");
			requestBody.put("from", from);
		
			JSONObject to = new JSONObject();
			JSONObject emailAddress = new JSONObject();
			emailAddress.put("address", toEmail);
			// emailAddress.put("name", emailRequest.getToName());
			to.put("email_address", emailAddress);
			requestBody.put("to", new JSONObject[]{to});
		
			requestBody.put("subject", "Update on Profile changes");
			String greeting="Thanks & Regards";
			String ofcName="RS Insurance pvt ltd.";
			String address1="Madhapur, Hyderabad,";
			String address2="Telangana, India. 500081";
		
			StringBuilder emailContent=new StringBuilder();
			
		//	String value = body.getStatus(); 
			
			 emailContent.append("Dear Customer,").append("<br/>").append("<br/>")
		     .append("We have received a request to update your credentials at RS Insurance pvt ltd. The status of your update request is <strong>").append("status : "+status).append("</strong>")
		     .append("<br/><br/>");
			
		//	emailContent.append("Dear Customer,").append("< br/>").append("< br/>")
		//	.append("We have received a request to update email address at RS Insurance pvt ltd. In order to finalize this change and we kindly ask you to verify this update by utilizing the OTP provided below:")
		////	.append("< h3>").append("OTP: "+otp).append("")
		//	.append("Please use this OTP to confirm the updation of your email address ").append("< br/>").append("< br/>")
		//	.append("If you did not initiate this change, or if you have any concerns, please contact our support team immediately at support@rsinsurance.com or +91 9951489432.").append("< br/>").append("< br/>")
		//	.append("If you did not request this OTP, please ignore this email.").append("< br/>").append("< br/>")
		//	.append("Thank you for choosing RS Insurance pvt ltd")
		//	.append("< h5>").append("< br/>")
		//	.append(greeting).append("< br/>")
		//	.append(ofcName).append("< br/>")
		//	.append(address1).append("< br/>")
		//	.append(address2).append("");
		
			 if(emailId != "" && mobileNo != "")
			 {
			  if ("approved".equalsIgnoreCase(status)) {
				  
		          emailContent.append("Congratulations! Your credentials of Email :"+emailId+"and Mobile :"+mobileNo+" Number update has been approved. Now, You Can Check in the Profile page")
		            .append("<br/><br/>");
		      } else if ("rejected".equalsIgnoreCase(status)) {
		          emailContent.append("Unfortunately, your credentials of Email:"+emailId+"and Mobile :"+mobileNo+" Number update request has been rejected. If you have any concerns, please contact our support team immediately at support@rsinsurance.com or +91 1800-143-123.")
		              .append("<br/><br/>");
		      }
			 }
			 else if(emailId != "" )
			 {
				 if ("approved".equalsIgnoreCase(status)) {
					  
			          emailContent.append("Congratulations! Your credentials for Email:"+emailId+" update has been approved. Now, You Can Check in the Profile page")
			            .append("<br/><br/>");
			      } else if ("rejected".equalsIgnoreCase(status)) {
			          emailContent.append("Unfortunately, your credentials for Email :"+emailId+" update request has been rejected. If you have any concerns, please contact our support team immediately at support@rsinsurance.com or +91 1800-143-123.")
			              .append("<br/><br/>");
			      }
			 }
			 else
			 {
				 if ("approved".equalsIgnoreCase(status)) {
					  
			          emailContent.append("Congratulations! Your credentials for Mobile "+mobileNo+" Number update has been approved. Now, You Can Check in the Profile page")
			            .append("<br/><br/>");
			      } else if ("rejected".equalsIgnoreCase(status)) {
			          emailContent.append("Unfortunately, your credentials for mobile "+mobileNo+" Number update request has been rejected. If you have any concerns, please contact our support team immediately at support@rsinsurance.com or +91 1800-143-123.")
			              .append("<br/><br/>");
			      }
			 }
		      emailContent.append("If you did not initiate this change, or if you have any concerns, please contact our support team immediately at support@rsinsurance.com or +91 1800-143-123.").append("<br/><br/>")
		          
		          .append("Thank you for choosing RS Insurance pvt ltd").append("<br/>")
		          .append(greeting).append("<br/>")
		          .append(ofcName).append("<br/>")
		          .append(address1).append("<br/>")
		          .append(address2).append("<br/>");
		
			String emailContentString = emailContent.toString();
		
		
		
		
		
			requestBody.put("htmlbody", emailContentString);
		
			OutputStream os = conn.getOutputStream();
			os.write(requestBody.toString().getBytes());
			os.flush();
		
			BufferedReader br;
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			} else {
			br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
			}
		
			String output;
			while ((output = br.readLine()) != null) {
			sb.append(output);
			}
		
			br.close();
			conn.disconnect();
		
			return "email is sended to "+toEmail;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "";
		}
	}
}
