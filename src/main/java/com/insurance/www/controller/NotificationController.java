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
import com.insurance.www.service.NotificationControllService;

@RestController
@RequestMapping("/api/notification/")
@CrossOrigin(origins="*")
public class NotificationController 
{
	
	@Autowired
	NotificationControllService notificationControllService;
	
	@Autowired
	CustomerSignupRepository cutCustomerSignupRepository;
	
	
	
	@GetMapping("/forMobile")
	public List<NotificationForMobile> getAllNotificationsFroMobile ()
	{
		return notificationControllService.getAllNotificationsFroMobile();
	}
	
	@GetMapping("/forMobile/{customerId}")
	public Optional<NotificationForMobile> getNotificationsFroMobile (@PathVariable String customerId)
	{
		return notificationControllService.getAllNotificationsFroMobileByCustomerId(customerId);
	}
	
	@PostMapping("/forMobile")
	public String sendNotificationforMobile(@RequestBody NotificationForMobile notificationForMobile)
	{
		return notificationControllService.sendNotificationforMobile(notificationForMobile);
	}
	
	@GetMapping("/getByStatusForMobile/{status}")
	public List<NotificationForMobile> getByStatusForMobile (@PathVariable String status)
	{
		return notificationControllService.getByStatusForMobile(status);
	}
	
	@PutMapping("/updateTheStatusForMobile/{customerId}")
	public String updateChangeStatus(@PathVariable String customerId , @RequestBody NotificationForMobile data )
	{
//		List<CustomerSignup> getExistingSignupDetails = cutCustomerSignupRepository.findByCustomerId(customerId);
//		CustomerSignup signupDetailsData = getExistingSignupDetails.get(0);
//		
//		NotificationRequestBody details = null;
//		details.setEmail(signupDetailsData.getEmail());
//		String mobileno = Long.toString( signupDetailsData.getMobileno());
//		details.setMobileNo(data.getMobileno);
//		details.setStatus(data.getStatus());
//		String email = signupDetailsData.getEmail();
//		
//		 emailorUpdation(email,details);
		String status = data.getStatus();
		String emailId = "";
		String mobileNo = data.getMobileNo();
		
		 emailorUpdation( customerId , status , emailId ,mobileNo);
		
		
		return notificationControllService.updateChangeStatus(customerId,data);
	}
	
	@GetMapping("/forEmail")
	public List<NotificationForEmail> getAllNotificationsForEmail ()
	{
		return notificationControllService.getAllNotificationsForEmail();
	}
	@GetMapping("/forEmail/{customerId}")
	public Optional<NotificationForEmail> getAllNotificationsForEmail (@PathVariable String customerId)
	{
		return notificationControllService.getAllNotificationsForEmailByCustomerId(customerId);
	}
	
	@PostMapping("/forEmail")
	public String sendNotificationForEmail(@RequestBody NotificationForEmail notificationForEmail)
	{
		return notificationControllService.sendNotificationForEmail(notificationForEmail);
	}
	
	@GetMapping("/getByStatusForEmail/{status}")
	public List<NotificationForEmail> getByStatusForEmail (@PathVariable String status)
	{
		return notificationControllService.getByStatusForEmail(status);
	}
	
	@PutMapping("/updateTheStatusForEmail/{customerId}")
	public String updateChangeStatusForEmail(@PathVariable String customerId , @RequestBody NotificationForEmail data )
	{
//		List<CustomerSignup> getExistingSignupDetails = cutCustomerSignupRepository.findByCustomerId(customerId);
//		CustomerSignup signupDetailsData = getExistingSignupDetails.get(0);
//		
//		NotificationRequestBody details = null;
//		details.setEmail(signupDetailsData.getEmail());
//		String mobileno = Long.toString( signupDetailsData.getMobileno());
//		details.setMobileNo(mobileno);
//		details.setStatus(data.getStatus());
//		String email = signupDetailsData.getEmail();
//		
//		 emailorUpdation(email,details);
		
		String status = data.getStatus();
		String emailId = data.getEmail();
		String mobileNo = "";
		
		 emailorUpdation( customerId , status , emailId ,mobileNo);
		
		return notificationControllService.updateChangeStatusForEmail(customerId,data);
		
		
	}
	
//	@GetMapping("/sendNotification/{toEmail}")
//	public String emailorUpdation(@PathVariable String toEmail,@RequestBody NotificationRequestBody body)
//	{
	public String emailorUpdation(String customerId , String status , String emailId ,String mobileNo)
	{
		
		List<CustomerSignup> getExistingSignupDetails = cutCustomerSignupRepository.findByCustomerId(customerId);
		CustomerSignup signupDetailsData = getExistingSignupDetails.get(0);
		String toEmail = signupDetailsData.getEmail();
		
	String postUrl = "https://api.zeptomail.in/v1.1/email";
	StringBuffer sb = new StringBuffer();

	try {

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
	requestBody.put("from", from);

	JSONObject to = new JSONObject();
	JSONObject emailAddress = new JSONObject();
	emailAddress.put("address", toEmail);
	// emailAddress.put("name", emailRequest.getToName());
	to.put("email_address", emailAddress);
	requestBody.put("to", new JSONObject[]{to});

	requestBody.put("subject", "Confirmation of New Email Address Updation");
	String greeting="Thanks & Regards";
	String ofcName="RS Insurance pvt ltd.";
	String address1="Madhapur, Hyderabad,";
	String address2="Telangana, India. 500081";

	StringBuilder emailContent=new StringBuilder();
	
//	String value = body.getStatus(); 
	
	 emailContent.append("Dear Customer,").append("<br/>").append("<br/>")
     .append("We have received a request to update your credentials at RS Insurance pvt ltd. The status of your update request is: <strong>").append("status:"+status).append("</strong>")
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
		  
          emailContent.append("Congratulations! Your credentials of Email :"+emailId+"and Mobile :"+mobileNo+" Number updated  has been approved. Now You Can Check in the Profile page")
            .append("<br/><br/>");
      } else if ("rejected".equalsIgnoreCase(status)) {
          emailContent.append("Unfortunately, your credentials of Email:"+emailId+"and Mobile :"+mobileNo+" Number update request has been rejected. If you have any concerns, please contact our support team immediately at support@rsinsurance.com or +91 99XXXXXXXX.")
              .append("<br/><br/>");
      }
	 }
	 else if(emailId != "" )
	 {
		 if ("approved".equalsIgnoreCase(status)) {
			  
	          emailContent.append("Congratulations! Your credentials for Email:"+emailId+" updated  has been approved. Now You Can Check in the Profile page")
	            .append("<br/><br/>");
	      } else if ("rejected".equalsIgnoreCase(status)) {
	          emailContent.append("Unfortunately, your credentials for Email :"+emailId+" update request has been rejected. If you have any concerns, please contact our support team immediately at support@rsinsurance.com or +91 99XXXXXXXX.")
	              .append("<br/><br/>");
	      }
	 }
	 else
	 {
		 if ("approved".equalsIgnoreCase(status)) {
			  
	          emailContent.append("Congratulations! Your credentials for Mobile "+mobileNo+" Number updated  has been approved. Now You Can Check in the Profile page")
	            .append("<br/><br/>");
	      } else if ("rejected".equalsIgnoreCase(status)) {
	          emailContent.append("Unfortunately, your credentials for mobile "+mobileNo+" Number update request has been rejected. If you have any concerns, please contact our support team immediately at support@rsinsurance.com or +91 99XXXXXXXX.")
	              .append("<br/><br/>");
	      }
	 }
      emailContent.append("If you did not initiate this change, or if you have any concerns, please contact our support team immediately at support@rsinsurance.com or +91 99XXXXXXXX.").append("<br/><br/>")
          
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
	} catch (Exception e) {
	e.printStackTrace();
	return "";
	}
	}

	@Autowired
	NotificationForEmailRepository notificationForEmailRepository;

	@Autowired
	NotificationForMobileRepository notificationForMobileRepository;

	@GetMapping("findByCustomerIdForMobile/{customerId}")
	public String getAllNotificationsFroMobileByCustomerId(@PathVariable String customerId) {
		String status = "request";
		Optional<NotificationForMobile> data = notificationForMobileRepository.findByCustomerIdAndStatus(customerId,status);
			if(data.isPresent()&& !data.isEmpty())
			{
				return "Details are already existed";
			}
			else
			{
				return "Details are not existed";
			}
	}
	
	

	@GetMapping("findByCustomerIdForEmail/{customerId}")
	public String getAllNotificationsForEmailByCustomerId(@PathVariable String customerId) {
		String status = "request";
		Optional<NotificationForEmail> data =  notificationForEmailRepository.findByCustomerIdAndStatus(customerId,status);
		if(data.isPresent()&& !data.isEmpty())
			{
				return "Details are already existed";
			}
			else
			{
				return "Details are not existed";
			}
	}

	@Autowired
	NotificationHystoryForEmailRepository notificationHystoryForEmailRepository;
	
	@Autowired
	NotificationHystoryForMobileRepository notificationHystoryForMobileRepository;

	@GetMapping("/getAllHystory/forEmail")
	public List<NotificationHystoryForEmail> getAllHystoryForEmail()
	{
		return notificationHystoryForEmailRepository.findAll();
	}
	
	@GetMapping("/getAllHystory/forMobile")
	public List<NotificationHystoryFormobile> getAllHystoryForMobile()
	{
		return notificationHystoryForMobileRepository.findAll();
	}

}