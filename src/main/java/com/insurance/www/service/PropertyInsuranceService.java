package com.insurance.www.service;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.insurance.CustomerDto;
import com.insurance.www.exception.ResourceNotFoundException;
// import com.insurance.www.model.CustomerLogin;
import com.insurance.www.model.CustomerPaymentDetails;
import com.insurance.www.model.CustomerSignup;
import com.insurance.www.model.EmailQuotePageEntity;
// import com.insurance.www.model.EmailRequest;
import com.insurance.www.model.FillDetails;
import com.insurance.www.model.QuoteDataTabularFormate;
import com.insurance.www.model.StructureAndDetails;
import com.insurance.www.repository.CustomerLoginRepository;
import com.insurance.www.repository.CustomerPaymentDetailsRepository;
import com.insurance.www.repository.CustomerSignupRepository;
import com.insurance.www.repository.EmailQuotePageRepo;
import com.insurance.www.repository.FillDetailsRepository;
import com.insurance.www.repository.PropertyInsuranceRepository;


@Service
public class PropertyInsuranceService 
{
	@Autowired
	PropertyInsuranceRepository propertyInsuranceRepository;
	
	public List<StructureAndDetails> getAllDetails()
	{
		return propertyInsuranceRepository.findAll();
	}
	public Optional<List<StructureAndDetails>> getStructureDetailsByCustomerId(String customerId) {
        Optional<List<StructureAndDetails>> structuresOptional = propertyInsuranceRepository.findByCustomerId(customerId);

	    if (structuresOptional.isPresent() && structuresOptional.get().isEmpty()) {
	         throw new ResourceNotFoundException("Customer Id Not Found");
			// Optional<ArrayList<String>> structuredetailsOptinalData =  new Optional<ArrayList<String>>();
			// return structuredetailsOptinalData.add("CustomerId was Not Found");
			
			// // return "Customr Id was Not Found";
	    }

	    return structuresOptional;
    }
	
	public StructureAndDetails createDetails( StructureAndDetails details )
	{
		return propertyInsuranceRepository.save(details);
	}	
	
	public Optional<StructureAndDetails> getStructureDetailsById(long id) {
		Optional<StructureAndDetails> structireTableDetails = propertyInsuranceRepository.findById(id);
		if (structireTableDetails.isEmpty()) {
			   throw new ResourceNotFoundException("Id Was Not Found");
		   }
	   return structireTableDetails;
    }
	
	
	 public String deleteStructureDetailsById(long id) {
		Optional<StructureAndDetails> existingDetailsOptional = propertyInsuranceRepository.findById(id);
		if (existingDetailsOptional.isPresent()) {
			propertyInsuranceRepository.deleteById(id);
			return "Structure details deleted successfully";
		} else {
			throw new ResourceNotFoundException("Id Was Not Found");
		}
	    }
	
	@Autowired
	CustomerSignupRepository customerSignupRepository;
	
	public List<CustomerSignup> getAllCustomers()
	{
		return customerSignupRepository.findAll();
	}
	public List<CustomerSignup> getCustomerById(String customerId) {
        return customerSignupRepository.findByCustomerId(customerId);
    }
	
	public CustomerSignup createCustomer(CustomerSignup customer)
	{
		
		LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateString = date.format(formattedDate);
        // String otp = formattedDateString.substring(formattedDateString.length() - 4);
        customer.setCustomerId(formattedDateString);
        
        
		return customerSignupRepository.save(customer);
	}
	
	 public List<CustomerSignup> getCustomerIdByMobileNo(String mobileno) 
	 {
		 return customerSignupRepository.findByMobileno(mobileno);
	 }
	
	@Autowired
	FillDetailsRepository fillDetailsRepository;
	
	public List<FillDetails> getAllData()
	{
		return fillDetailsRepository.findAll();
	}
	
	public Optional<FillDetails> getFillDetailsById(long id)
	{
		return fillDetailsRepository.findById(id);
	}
	
	public List<FillDetails> getFillDetailsByCustomerId(String customerId)
	{
		return fillDetailsRepository.findByCustomerId(customerId);
	}
	public FillDetails createData(FillDetails data )
	{
		return fillDetailsRepository.save(data);
	}
	
	
	
	@Autowired
	CustomerPaymentDetailsRepository customerPaymentDetailsRepository;
	public List<CustomerPaymentDetails> getPaymentData()
	{
		return customerPaymentDetailsRepository.findAll();
	}
	

	
	public List<CustomerPaymentDetails> getPaymentDetailsByCustomerId(@PathVariable String customerId)
	{
		return customerPaymentDetailsRepository.findByCustomerId(customerId);
	}
	
	public CustomerPaymentDetails createPaymentData(CustomerPaymentDetails data )
	{
        data.setStartingDate(LocalDate.now());
        data.setExpiryDate(data.getStartingDate().minusDays((1)).plusYears(data.getYear()));
		return customerPaymentDetailsRepository.save(data);
    }
	
	@Autowired
	CustomerLoginRepository customerLoginRepository;

	// public String login(CustomerLogin customerLogin) {
	//     CustomerSignup user = customerSignupRepository.findByMobilenoAndPassword(customerLogin.getMobileno(), customerLogin.getPassword());
	    
	//     if (user == null) {
	//         return "Invalid username or password.";
	//     }

	//     else if (user.getPassword().equals(customerLogin.getPassword())) {
	//         return "Login successful!";
	//     } else {
	//         return "Invalid username or password.";
	//     }
	// }
	
	public ResponseEntity<String> checkMobileNumber(String mobileNumber) 
	{
		List<CustomerSignup> customers = customerSignupRepository.findByMobileno(mobileNumber);
	    if (!customers.isEmpty()) {
	        return ResponseEntity.ok("Mobile number exists");
	    } else {
	        return ResponseEntity.ok("Mobile number is not exists");
	    }
	}

	public ResponseEntity<String> checkEmail(String email) {
		List<CustomerSignup> customers = customerSignupRepository.findByEmail(email);
	    if (!customers.isEmpty()) {
	        return ResponseEntity.ok("Email is exists");
	    } else {
	        return ResponseEntity.ok("Email is not exists");
	    }
	}
	

    public String deleteById(long id) {
    	
        if (customerSignupRepository.existsById(id)) {
            customerSignupRepository.deleteById(id);
            return "Deleted";
        } else {
            return "Not Found";
        }
    }

    public String deleteFillDetailsById(long id) {
        if (fillDetailsRepository.existsById(id)) {
            fillDetailsRepository.deleteById(id);
            return "Deleted";
        } else {
            return "Not Found";
        }
    }

    public String deletePaymentDetailsById(long id) {
        if (customerPaymentDetailsRepository.existsById(id)) {
            customerPaymentDetailsRepository.deleteById(id);
            return "Deleted";
        } else {
            return "Not Found";
        }
    }

    public String updateStructureDetailsById(long id, StructureAndDetails details) {
        Optional<StructureAndDetails> existingDetailsOptional = propertyInsuranceRepository.findById(id);
        if (existingDetailsOptional.isPresent()) {
            StructureAndDetails existingDetails = existingDetailsOptional.get();
           
            existingDetails.setMarketValue(details.getMarketValue());
            existingDetails.setSquareFeet(details.getSquareFeet());
            existingDetails.setPincode(details.getPincode());
            existingDetails.setBuildingAge(details.getBuildingAge());
            existingDetails.setSecurity(details.getSecurity());
            existingDetails.setEffected(details.getEffected());
            existingDetails.setPerson(details.getPerson());
            
           propertyInsuranceRepository.save(existingDetails);
           return "Updated SuccessFully";
        } else {
            return "Id was notFound";
        }
    }

    public String updateFillDetailsById(long id, FillDetails fillDetails) {
        Optional<FillDetails> existingFillDetailsOptional = fillDetailsRepository.findById(id);
        
        if (existingFillDetailsOptional.isPresent()) {
            FillDetails existingFillDetails = existingFillDetailsOptional.get();
            
            existingFillDetails.setPincode(fillDetails.getPincode());
            existingFillDetails.setHouseno(fillDetails.getHouseno());
            existingFillDetails.setStreetno(fillDetails.getStreetno());
            existingFillDetails.setCity(fillDetails.getCity());
            existingFillDetails.setState(fillDetails.getState());
            
            fillDetailsRepository.save(existingFillDetails);
            return "updated successfully";
        } else {
            return "records not updated";
        }
    }
    
    public String updateFillDetailsPageById(@PathVariable long id, @RequestBody FillDetails fillDetails) {

    	 Optional<FillDetails> existingFillDetailsOptional = fillDetailsRepository.findById(id);
         
         if (existingFillDetailsOptional.isPresent()) {
             FillDetails existingFillDetails = existingFillDetailsOptional.get();
             existingFillDetails.setCurrentaddress(fillDetails.getCurrentaddress());
             existingFillDetails.setDob(fillDetails.getDob());
             existingFillDetails.setFullname(fillDetails.getFullname());
             existingFillDetails.setPancard(fillDetails.getPancard());
             existingFillDetails.setGender(fillDetails.getGender());
             existingFillDetails.setPincode(fillDetails.getPincode());
             existingFillDetails.setHouseno(fillDetails.getHouseno());
             existingFillDetails.setStreetno(fillDetails.getStreetno());
             existingFillDetails.setPropertypincode(fillDetails.getPropertypincode());
             existingFillDetails.setPropertyhouseNo(fillDetails.getPropertyhouseNo());
             existingFillDetails.setPropertystreetNo(fillDetails.getPropertystreetNo());
             
             fillDetailsRepository.save(existingFillDetails);
             return "updated successfully";
         } else {
             return "records not updated";
         }
		
	}
    
//    public String updateFillDetailsByCustomerId(@PathVariable String customerId, @RequestBody FillDetails fillDetails) {
//
//    	 Optional<List<FillDetails>> existingFillDetailsOptional = Optional.of(fillDetailsRepository.findByCustomerId(customerId));
//         
//         if (((Optional<List<FillDetails>>) existingFillDetailsOptional).isPresent()) {
//             List<FillDetails> existingFillDetails = existingFillDetailsOptional.get();
//             
//             ((FillDetails) existingFillDetails).setPincode(fillDetails.getPincode());
//             ((FillDetails) existingFillDetails).setHouseno(fillDetails.getHouseno());
//             ((FillDetails) existingFillDetails).setStreetno(fillDetails.getStreetno());
//             
//             fillDetailsRepository.save(existingFillDetails);
//             return "updated successfully";
//         } else {
//             return "records not updated";
//         }
//	}

    public String updatePaymentDetailsById(long id, CustomerPaymentDetails paymentDetails) {
        Optional<CustomerPaymentDetails> existingPaymentDetailsOptional = customerPaymentDetailsRepository.findById(id);
        if (existingPaymentDetailsOptional.isPresent()) {
            CustomerPaymentDetails existingPaymentDetails = existingPaymentDetailsOptional.get();
           
            existingPaymentDetails.setYear(paymentDetails.getYear());
            existingPaymentDetails.setPremium(paymentDetails.getPremium());
            
             customerPaymentDetailsRepository.save(existingPaymentDetails);
             return "Updated Successfully";
        } else {
            return null;
        }
    }

    public String updateCustomerById(long id, CustomerSignup customer) {
        Optional<CustomerSignup> existingCustomerOptional = customerSignupRepository.findById(id);
        if (existingCustomerOptional.isPresent()) {
            CustomerSignup existingCustomer = existingCustomerOptional.get();
            
            existingCustomer.setName(customer.getName());
            existingCustomer.setMobileno(customer.getMobileno());
            existingCustomer.setEmail(customer.getEmail());
//            existingCustomer.setPassword(customer.getPassword());
            
           customerSignupRepository.save(existingCustomer);
           return "Updated SuccessFully";
        } else {
            return "Not Updated";
        }
    }
    
    public String updateCustomerByMobileNo(Long id, String mobileno) {
    	Optional<CustomerSignup> existingCustomerOptional = customerSignupRepository.findById(id);
        if (existingCustomerOptional.isPresent()) {
            CustomerSignup existingCustomer = existingCustomerOptional.get();
           

            existingCustomer.setMobileno(mobileno);
            
           customerSignupRepository.save(existingCustomer);
           return "Updated SuccessFully";
        } else {
            return "Not Updated";
        }

      
	   
	}
    
    public String updateCustomerByEmailId( long id, String emailId) {
    	Optional<CustomerSignup> existingCustomerOptional = customerSignupRepository.findById(id);
        if (existingCustomerOptional.isPresent()) {
            CustomerSignup existingCustomer = existingCustomerOptional.get();
         
            existingCustomer.setEmail(emailId);
    
           customerSignupRepository.save(existingCustomer);
           return "Updated SuccessFully";
        } else {
            return "Not Updated";
        }
	}
    
    // public String updateCustomerPasswordByid(@PathVariable long id, @RequestBody CustomerSignup customer) {
    // 	 Optional<CustomerSignup> existingCustomerOptional = customerSignupRepository.findById(id);
    	 
    //     if (existingCustomerOptional.isPresent()) {
    //         CustomerSignup existingCustomer = existingCustomerOptional.get();
           
    //         existingCustomer.setPassword(customer.getPassword());
            
    //         customerSignupRepository.save(existingCustomer);
    //         return "Password Updated Successfully";
    //     } else {
    //         return "PassWord not Updated ";
    //     }

    // }
    
    
    public String sendSms(String mobileno) {
    	
    	String user = "XXXXXX"; // your username
        String password = "XXXXXX"; // your password
        String senderId = "WELCOM"; // Your senderid
        String mobileNumbers = "9XXXXXXXXX"; // enter Mobidele numbers comma separated
        String message = "test message"; // enter Your Message

        String url = "https://login4.spearuc.com/MOBILE_APPS_API/sms_api.php?type=smsquicksend&user=qtnextotp&pass=987654&sender=RamanaSoft &t_id=1707170494921610008&to_mobileno="+mobileno+"&sms_text=Dear customer, use this OTP 123455 to signup in to the Insurance PolocyDetails Page. This OTP will be valid for the next 15 mins";
        RestTemplate restTemplate = new RestTemplate();

        String requestBody = "User=" + user + "&passwd=" + password + "&mobilenumber=" + mobileNumbers +
                "&message=" + message + "&sid=" + senderId;

        String response = restTemplate.postForObject(url, requestBody, String.class);
        return response;
    }
    
    public String getCurrentDateTime() {
  
    	LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateString = date.format(formattedDate);
        String otp = formattedDateString.substring(formattedDateString.length() - 4);
        return otp;
       
    }
    

	// 	@Value("${spring.mail.username}")
	// 	private String sender;
		
	// 	@Autowired 
	// 	JavaMailSender javaMailSender;
		
	// 	SimpleMailMessage smm= new SimpleMailMessage();
    
    // public String sendMail(String mail)
    // {
    	
    // LocalDateTime date = LocalDateTime.now();
    // DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    // String formattedDateString = date.format(formattedDate);
    // String otp = formattedDateString.substring(formattedDateString.length() - 4);

    // String msg="Dear Custumer,"
    // + "Otp to to change the email in Rs Insurance pvt ltd. Here is you 4 digits Otp "+otp;




    // smm.setFrom(sender);
    // smm.setTo(mail);
    // smm.setText(msg);
    // smm.setSubject("Email Verification for RS Insurance pvt ltd.");
    // javaMailSender.send(smm);
    
    
    // return otp;

    // }/

    // String otp=getCurrentDateTime();

    public String generateOTP() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateString = date.format(formattedDate);
        String otp = formattedDateString.substring(formattedDateString.length() - 4);
        return otp;
    }

    // for signup email otp
	public String sendEmail(String emailRequest) {
        String postUrl = "https://api.zeptomail.in/v1.1/email";
        StringBuffer sb = new StringBuffer();
        String otp=generateOTP();
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
            from.put("name", "RS Insurance");
            from.put("address", email);

            requestBody.put("from", from);

            JSONObject to = new JSONObject();
            JSONObject emailAddress = new JSONObject();
            emailAddress.put("address", emailRequest);

            to.put("email_address", emailAddress);
            requestBody.put("to", new JSONObject[]{to});

            requestBody.put("subject", "Verify Your Email Address");
            String greeting="Thanks & Regards";
            String ofcName="RS Insurance pvt ltd.";
            String address1="Madhapur, Hyderabad,";
            String address2="Telangana, India. 500081";
            
            StringBuilder emailContent=new StringBuilder();
            emailContent.append("Dear Customer,").append("<br/>").append("<br/>")
            .append("Thank you for signing up with RS Insurance pvt ltd. To complete your registration, please use the OTP provided below to verify your email address.").append("<br/>")
            .append("<h3>").append("OTP: "+otp).append("</h3>")
            .append("Please keep this OTP confidential and do not share it with anyone.").append("<br/>").append("<br/>")
            .append("If you did not request this OTP, please ignore this email.").append("<br/>").append("<br/>")
            .append("Thank you for choosing RS Insurance pvt ltd")
            .append("<h5>").append("<br/>")
            .append(greeting).append("<br/>")
            .append(ofcName).append("<br/>")
            .append(address1).append("<br/>")
            .append(address2).append("</h5>");
            

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

            return otp;
        } catch (Exception e) {
            e.printStackTrace();
            return otp;
        }
    }

    public String generateOTPforupdation() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateString = date.format(formattedDate);
        String otp = formattedDateString.substring(formattedDateString.length() - 4);
        StringBuffer sb= new StringBuffer(otp);
        otp=sb.reverse().toString();
        return otp;
    }

    //for emailotp updation
    public String EmailUpdation(String toEmail) {
        
        String postUrl = "https://api.zeptomail.in/v1.1/email";
        StringBuffer sb = new StringBuffer();
        String otp=generateOTPforupdation();
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
            from.put("name", "RS Insurance");
            requestBody.put("from", from);

            JSONObject to = new JSONObject();
            JSONObject emailAddress = new JSONObject();
            emailAddress.put("address", toEmail);
//            emailAddress.put("name", emailRequest.getToName());
            to.put("email_address", emailAddress);
            requestBody.put("to", new JSONObject[]{to});

            requestBody.put("subject", "Confirmation of New Email Address Updation");
            String greeting="Thanks & Regards";
            String ofcName="RS Insurance pvt ltd.";
            String address1="Madhapur, Hyderabad,";
            String address2="Telangana, India. 500081";
            
            StringBuilder emailContent=new StringBuilder();
            emailContent.append("Dear Customer,").append("<br/>").append("<br/>")
            .append("We have received a request to update email address at RS Insurance pvt ltd. In order to finalize this change and we kindly ask you to verify this update by utilizing the OTP provided below:")
            .append("<h3>").append("OTP: "+otp).append("</h3>")
            .append("Please use this OTP to confirm the updation of your email address ").append("<br/>").append("<br/>")
            .append("If you did not initiate this change, or if you have any concerns, please contact our support team immediately at support@ramanasoft.com or 1800-258-2465.").append("<br/>").append("<br/>")
            .append("If you did not request this OTP, please ignore this email.").append("<br/>").append("<br/>")
            .append("Thank you for choosing RS Insurance pvt ltd")
            .append("<h5>").append("<br/>")
            .append(greeting).append("<br/>")
            .append(ofcName).append("<br/>")
            .append(address1).append("<br/>")
            .append(address2).append("</h5>");
            

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

            return otp;
        } catch (Exception e) {
            e.printStackTrace();
            return otp;
        }
    }

   
    public LocalDateTime generateCreateDate() {
        LocalDateTime dateTime = LocalDateTime.now();

//	        // Define a custom date and time format
//	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//	        // Format the LocalDateTime object using the defined format
//	        String formattedDateTime = dateTime.format(formatter);
        return dateTime;
    }
    public String generateUniqueId() {
        //	        LocalDateTime date = LocalDateTime.now();
        //	        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        //	        String formattedDateString = date.format(formattedDate);
        //	        String otp = formattedDateString.substring(formattedDateString.length() - 4);
        //	        return otp;
                    
                    String uniqueID=UUID.randomUUID().toString();
                    String finalUniqueID=uniqueID.substring(0,8);
                    return finalUniqueID;
                }

                @Autowired
                EmailQuotePageRepo eqpRepo;

    // for emailsending at quotepage
    public String sendEmailQuotePage(String toEmail, EmailQuotePageEntity eqpEntity) {
        String postUrl = "https://api.zeptomail.in/v1.1/email";
	        StringBuffer sb = new StringBuffer();
	        LocalDateTime createDate=generateCreateDate();
	        String UniqueId= generateUniqueId();
	        try {
	        	eqpEntity.setCreateDate(createDate);
	        	eqpEntity.setUniqueId(UniqueId);
	        	
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
                from.put("name", "RS Insurance");
	            requestBody.put("from", from);

	            JSONObject to = new JSONObject();
	            JSONObject emailAddress = new JSONObject();
	            emailAddress.put("address", toEmail);
//	            emailAddress.put("name", "Sushm");
	            to.put("email_address", emailAddress);
	            requestBody.put("to", new JSONObject[]{to});

	            requestBody.put("subject", "Find Your Next Investment with RS Insurace pvt ltd.");
	            String greeting="Thanks & Regards";
	            String name="Sushmidhar";
	            String role="Customer Support Executive";
	            String ofcName="RS Insurance pvt ltd.";
	            String phNo="1800-143-123";
	            String mail="rspropertyinsurance@gmail.com";
	            String address1="Madhapur, Hyderabad,";
	            String address2="Telangana, India. 500081";
	            
            	
            String link = " http://localhost:9191/api/propertyinsurance/getQuoteEmail/"+ UniqueId;
            
	            
	            StringBuilder emailContent = new StringBuilder();
	            emailContent.append("Dear Customer,").append("<br/>").append("<br/>")
	            .append("Thank you for choosing RS Insurance.").append("<br/>").append("<br/>")
	            .append("Please click the below URL to see your property details from RSInsurance Pvt Ltd: ")
	            .append(link).append("<br/>")
	            .append("We appreciate your trust in our services and look forward to continuing to serve your insurance needs.").append("<br/>").append("<br/>")
	            .append("<h5>").append("<br/>")
//	            .append(name).append("<br/>")
//	            .append(role).append("<br/>")
	            .append(greeting).append("<br/>")
	            .append(ofcName).append("<br/>")
	            .append(phNo).append("<br/>")
	            .append(mail).append("<br/>")
	            .append(address1).append("<br/>")
	            .append(address2).append("</h5>");
	            

	            String emailContentString = emailContent.toString();


	            requestBody.put("htmlbody",emailContentString);
	            eqpRepo.save(eqpEntity);
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
	           
//	            RestTemplate
	            
//	            eqpRepo.save(eqpEntity);

	            return "quote email succesfully";
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "issue with quote email sending proccess";
	        }
    }

// Data expiration after 15days

    @Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Kolkata")
									// Run at midnight on the 1st of every month
	    public void deleteExpiredData() {
	        // Calculate the date one month ago
	        LocalDate oneMonthAgo = LocalDate.now().minusDays(15);

	        // Convert to LocalDateTime for comparison with timestamp in database
	        LocalDateTime oneMonthAgoDateTime = oneMonthAgo.atStartOfDay();

	        // Query the database for data older than one month
//	        List<EmailQuotePageEntity> expiredData = signupRepo.findByCreatedDateBefore(LocalDateTime oneMonthAgoDateTime);
	        List<EmailQuotePageEntity> expiredData=eqpRepo.findByCreateDateBefore(oneMonthAgoDateTime);
	     
	        // Delete the expired data
	        eqpRepo.deleteAll(expiredData);
	    }

        //to send an email to user at quotepage
        public String sendEmailQuotePageTabularFormate(String emailRequest, QuoteDataTabularFormate  qdtf)
		{
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
                from.put("name", "RS Insurance");
	            from.put("address", email);
	            requestBody.put("from", from);

	            JSONObject to = new JSONObject();
	            JSONObject emailAddress = new JSONObject();
	            emailAddress.put("address", emailRequest);

	            to.put("email_address", emailAddress);
	            requestBody.put("to", new JSONObject[]{to});

	            requestBody.put("subject", "Find Your Next Investment with RS Insurace pvt ltd.");
	            List<QuoteDataTabularFormate > data = Arrays.asList(
	                    new QuoteDataTabularFormate(qdtf.getMarketValue(),qdtf.getSquareFeet(),qdtf.getPincode(), qdtf.getYear(), qdtf.getPremium())

	            );

	            StringBuilder html = new StringBuilder();
                html.append("<html>");
		    	html.append("<head>");
		    	html.append("<title>Quotation Details</title>");
		    	html.append("<style>");
		    	html.append("table, th, td { border: 1px solid black; border-collapse: collapse; }");
		    	html.append("th, td { padding: 10px 20px; font-size: 16px;}"); // Adjust the padding values as needed
		    	html.append("</style>");
		    	html.append("</head>");
		    	html.append("<body>");
		    	html.append("<h1>Quotation Details</h1>");
		    	html.append("<table>");
		    	html.append("<tr><th>Market Value</th><th>Carpet Value</th><th>Property Pincode</th><th>Premium Year</th><th>Premium Amount</th></tr>");
	            for (QuoteDataTabularFormate row : data) {
	                html.append("<tr>")
	                    .append("<td>").append(row.getMarketValue()).append("</td>")
	                    .append("<td>").append(row.getSquareFeet()).append("</td>")
	                    .append("<td>").append(row.getPincode()).append("</td>")
	                    .append("<td>").append(row.getYear()).append("</td>")
	                    .append("<td>").append(row.getPremium()).append("</td>")
	                    .append("</tr>");
	            }

	            html.append("</table>");
	            html.append("</body>");
	            html.append("</html>");
	            
	            String greeting="Thanks & Regards";
	            String ofcName="RS Insurance pvt ltd.";
	            String phNo="1800-258-2465";
	            String mail="support@ramanasoft.com";
	            String address1="Madhapur, Hyderabad,";
	            String address2="Telangana, India. 500081";
	            

	            
	            StringBuffer stringBuffer=new StringBuffer();
	            stringBuffer.append("Dear Customer,").append("<br/>").append("<br/>")
	            .append("Thank you for choosing RS Insurance.").append("<br/>")
	            .append("<br/>")
	            .append("Here is your Quotation Details").append("<br/>").append("<br/>")
	            .append("We appreciate your trust in our services and looking forward to serve your insurance needs.").append("<br/>").append("<br/>")
	            .append("<h5>")
	            .append(html).append("<br/>").append("<br/>")
	            .append(greeting).append("<br/>")
	            .append(ofcName).append("<br/>")
	            .append(phNo).append("<br/>")
	            .append(mail).append("<br/>")
	            .append(address1).append("<br/>")
	            .append(address2).append("</h5>");
	             html.toString();
	            requestBody.put("htmlbody",stringBuffer.toString());
	            
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
	           
//	            RestTemplate
	            
//	            eqpRepo.save(eqpEntity);

	            return "quote email succesfully";
	        
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "issue with quote email sending proccess";
	        }
	        
	        

		}


        

        public String checkEligibility1(FillDetails request) 
        {
            Optional<FillDetails> records =fillDetailsRepository.findByPropertyhouseNoAndPropertystreetNoAndPropertypincodeAndPropertystateAndPropertycity(request.getPropertyhouseNo(),
                    request.getPropertystreetNo(), request.getPropertypincode(),request.getPropertystate(),request.getPropertycity());
            
             if (records.isPresent() ) {
                 return "Records are present";
             }
             else
             {
              return "Records are empty";
             }
        }


        public CustomerDto findByMobileNumber(String mobileno) {

List< CustomerSignup> customers = customerSignupRepository.findByMobileno(mobileno);
CustomerDto customerDto = new CustomerDto(); // Initialize CustomerDto

if (customers.isEmpty()) {
customerDto.setMobileno("");
customerDto.setEmail("");
return customerDto;
} else {
// Assuming you want to return the mobileno of the first customer found

CustomerSignup customer = customers.get(0);

List< FillDetails> fillDetailsList = fillDetailsRepository.findByCustomerId(customer.getCustomerId());

if (!fillDetailsList.isEmpty()) {
FillDetails fillDetails = fillDetailsList.get(0);
customerDto.setGender(fillDetails.getGender());
// customerDto.setFullname(fillDetails.getFullname());
customerDto.setDob(fillDetails.getDob());
customerDto.setPancard(fillDetails.getPancard());

}
customerDto.setMobileno(customer.getMobileno());
customerDto.setEmail(customer.getEmail());
customerDto.setFullname(customer.getName());

return customerDto;
}
}
}
