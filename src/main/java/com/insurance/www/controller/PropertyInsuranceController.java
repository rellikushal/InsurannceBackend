package com.insurance.www.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.CustomerDto;
// import com.insurance.www.model.CustomerLogin;
import com.insurance.www.model.CustomerPaymentDetails;
import com.insurance.www.model.CustomerSignup;
import com.insurance.www.model.EmailQuotePageEntity;
// import com.insurance.www.model.EmailRequest;
import com.insurance.www.model.FillDetails;
import com.insurance.www.model.QuoteDataTabularFormate;
import com.insurance.www.model.StructureAndDetails;
import com.insurance.www.service.InvoiceService;
import com.insurance.www.service.PropertyInsuranceService;
import com.lowagie.text.DocumentException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

// (origins = "http://192.168.1.12:9093")
@CrossOrigin(origins = "*")  
// @CrossOrigin(origins = "http://192.168.1.2:9093")
@RestController
@RequestMapping(path = "/api/v1/", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(
	name="CRUD REST APIs for PropertyInsurance",
	description = " CRUD rest api in propertyinsurance to CREATE, UPDATE, FETCH AND DELETE insurance details"
)
@Validated
public class PropertyInsuranceController 
{
	
	
	@Autowired
	PropertyInsuranceService propertyInsuranceService;
	@Operation(
		summary = " Getting All structure details",
		description = " get api to fetch all the insurances that sold"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " http status ok"
		)
	})
	@GetMapping("/getStructure")
	public List<StructureAndDetails> getAllDetails()
	{
		return propertyInsuranceService.getAllDetails();
	}

	@Operation(
		summary = " Getting a single Customer All structure details based on customerId ",
		description = " get api to fetch all the insurances that sold to a customer"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "http status ok"
		)
	})
	@GetMapping("/getStructureByCustomerId/{customerId}")
    public Optional<List<StructureAndDetails>> getStructureDetailsByCustomerId(@PathVariable String customerId) {
        return propertyInsuranceService.getStructureDetailsByCustomerId(customerId);
    }
	@Operation(
		summary = " Getting a single structure details based on Id",
		description = " get api to fetch a single insurances that sold"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " http status ok"
		)
	})
	@GetMapping("/getStructureDetailsById/{id}")
    public Optional<StructureAndDetails> getStructureDetailsById(@PathVariable long id) {
        return propertyInsuranceService.getStructureDetailsById(id);
    }
	@Operation(
		summary = " putting a single structure details",
		description = " REST api to CREATE a single insurances that sold"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "201",
			description = " Http status Created"
		)
	})
	@PostMapping("/putStructure")
	public StructureAndDetails createDetails(@RequestBody StructureAndDetails details )
	{
		return propertyInsuranceService.createDetails(details);
	}
	
	
	@Operation(
		summary = " Deleting a single structure details based on Id",
		description = " REST api to delete a single insurances that sold"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "201",
			description = " Http status Created"
		)
	})
	// delete the details of the structure details :
	 @DeleteMapping("/deleteStructureDetails/{id}")
	    public String deleteStructureDetailsById(@PathVariable long id) {
	        return propertyInsuranceService.deleteStructureDetailsById(id);
	        // if (result.equals("Deleted")) {
	        //     return ResponseEntity.ok().body("Structure details deleted successfully");
	        // } else {
	        //     return ResponseEntity.notFound().build();
	        // }
	    }
	
		@Operation(
			summary = " Getting All customers ",
			description = " REST api to fetch all the customers that are signed up"
		)
		@ApiResponses({
			@ApiResponse(
				responseCode = "200",
				description = " Http status ok"
			)
		})
	
	@GetMapping("/getCustomer")
	public List<CustomerSignup> getAllCustomers()
	{
		return propertyInsuranceService.getAllCustomers();
	}
	@Operation(
			summary = " Getting a customers based on customerId ",
			description = " REST api to fetch a customers"
		)
		@ApiResponses({
			@ApiResponse(
				responseCode = "200",
				description = " Http status ok"
			)
		})
	 @GetMapping("/getCustomerById/{customerId}")
	  public List<CustomerSignup> getCustomerById(@PathVariable String customerId)
	 {
	        return propertyInsuranceService.getCustomerById(customerId);
	 }
	 @Operation(
			summary = " Getting A customer based on mobileNumber ",
			description = " REST api to fetch a customer"
		)
		@ApiResponses({
			@ApiResponse(
				responseCode = "200",
				description = " Http status ok"
			)
		})
		// List<CustomerSignup> 
	 @GetMapping("/getCustomerByMobileNumber/{mobileno}")
	  public List<CustomerSignup> getCustomerIdByMobileNo( @Pattern(regexp = "(^[6-9][0-9]{9}$)", message = "mobileNumber must be start with 6 to 9 and have 10 digits")@PathVariable String mobileno) 
	 {
	    
			return propertyInsuranceService.getCustomerIdByMobileNo(mobileno);
	  }
	  @Operation(
		summary = " Creating  A customer ",
		description = " REST api to Create a customer"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "201",
			description = " Http status Created"
		)
	})  
	@PostMapping("/putCustomer")
	public CustomerSignup createCustomer(@Valid @RequestBody CustomerSignup customer)
	{
		return propertyInsuranceService.createCustomer(customer);
	}
	@Operation(
			summary = " Getting All user details related to property ",
			description = " REST api to fetch all the fill Details"
		)
		@ApiResponses({
			@ApiResponse(
				responseCode = "200",
				description = " Http status ok"
			)
		})
	@GetMapping("/getData")
	public List<FillDetails> getAllData()
	{
		return propertyInsuranceService.getAllData();
	}
	@Operation(
			summary = " Getting A single user details related to property by Id ",
			description = " REST api to fetch a fill Details"
		)
		@ApiResponses({
			@ApiResponse(
				responseCode = "200",
				description = " Http status ok"
			)
		})
	@GetMapping("/getFillDetailsDataById/{id}")
	public Optional<FillDetails> getFillDetailsById(@PathVariable long id)
	{
		return propertyInsuranceService.getFillDetailsById(id);
	}
	@Operation(
		summary = " Getting A single user details related to property by CustomerId ",
		description = " REST api to fetch a fill Details"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " Http status ok"
		)
	})
	@GetMapping("/getfillDetailsByCustomerId/{customerId}")
	public List<FillDetails> getFillDetailsByCustomerId(@PathVariable String customerId)
	{
		return propertyInsuranceService.getFillDetailsByCustomerId(customerId);
	}
	@Operation(
		summary = " Creating  A user details related to property ",
		description = " REST api to Create a fill details"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "201",
			description = " Http status Created"
		)
	})  
	@PostMapping("/putData")
	public FillDetails createData(@Valid@RequestBody FillDetails data )
	{
		return propertyInsuranceService.createData(data);
	}
	@Operation(
			summary = " Getting All Payment details that are made ",
			description = " REST api to fetch all the payment Details"
		)
		@ApiResponses({
			@ApiResponse(
				responseCode = "200",
				description = " Http status ok"
			)
		})
	@GetMapping("/getPaymentData")
	public List<CustomerPaymentDetails> getPaymentData()
	{
		return propertyInsuranceService.getPaymentData();
	}
	@Operation(
			summary = " Getting All Payment details that are made by a customer by a customerId ",
			description = " REST api to fetch all the payment Details "
		)
		@ApiResponses({
			@ApiResponse(
				responseCode = "200",
				description = " Http status ok"
			)
		})
	@GetMapping("/getPaymentDetailsByCustomerId/{customerId}")
	public List<CustomerPaymentDetails> getPaymentDetailsByCustomerId(@PathVariable String customerId)
	{
		return propertyInsuranceService.getPaymentDetailsByCustomerId(customerId);
	}
	@Operation(
		summary = " Creating  A Payment details related to a payment ",
		description = " REST api to Create a payment"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "201",
			description = " Http status Created"
		)
	})  
	@PostMapping("/putPaymentData")
	public CustomerPaymentDetails createPaymentData(@RequestBody CustomerPaymentDetails data )
	{
		return propertyInsuranceService.createPaymentData(data);
	}
	// @Operation(
	// 	summary = " Creating  A Payment details related to a payment  ***** ",
	// 	description = " REST api to Create a payment"
	// )
	// @ApiResponses({
	// 	@ApiResponse(
	// 		responseCode = "201",
	// 		description = " Http status Created"
	// 	)
	// })  
	// @PostMapping("/login")
	// public String login(@Valid@RequestBody CustomerLogin customerLogin ) 
	// {
	// 	return propertyInsuranceService.login(customerLogin);
	// }
	@Operation(
		summary = " checking a mobileNumber if it exists in the dtabase or not",
		description = " REST api to check a mobileNumber"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " Http status ok"
		)
	})
	@GetMapping("/checkMobileNumber/{mobileNumber}")
	public ResponseEntity<String> checkMobileNumber(@Pattern(regexp = "(^[6-9][0-9]{9}$)", message = "mobileNumber must be start with 6 to 9 and have 10 digits")@PathVariable String mobileNumber) {
		return propertyInsuranceService.checkMobileNumber(mobileNumber);
	}
	@Operation(
		summary = " checking a Email if it exists in the dtabase or not",
		description = " REST api to check a Email"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " Http status ok"
		)
	})
	@GetMapping("/checkEmail/{email}")
	public ResponseEntity<String> checkEmail(@PathVariable String email) {
		return propertyInsuranceService.checkEmail(email);
	}
	@Operation(
		summary = " Deleting a customer by Id ",
		description = " REST api to delete a customer"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " Http status ok"
		)
	})
	@DeleteMapping("/deleteCustomer/{id}")
	public ResponseEntity<String> deleteById(@PathVariable long id) {
	    String result = propertyInsuranceService.deleteById(id);
	    if (result.equals("Deleted")) {
	        return ResponseEntity.ok().body("Customer deleted successfully");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	@Operation(
		summary = " Deleting a fill Details related to property by Id ",
		description = " REST api to delete a fill details"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " Http status ok"
		)
	})
	@DeleteMapping("/deleteFillDetails/{id}")
	public ResponseEntity<String> deleteFillDetailsById(@PathVariable long id) {
	    String result = propertyInsuranceService.deleteFillDetailsById(id);
	    if (result.equals("Deleted")) {
	        return ResponseEntity.ok().body("Fill details deleted successfully");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	@Operation(
		summary = " Deleting a paaymentdetails by Id ",
		description = " REST api to delete a payment details"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " Http status ok"
		)
	})
	@DeleteMapping("/deletePaymentDetails/{id}")
	public ResponseEntity<String> deletePaymentDetailsById(@PathVariable long id) {
	    String result = propertyInsuranceService.deletePaymentDetailsById(id);
	    if (result.equals("Deleted")) {
	        return ResponseEntity.ok().body("Payment details deleted successfully");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	@Operation(
		summary = " updating a structure details by Id ",
		description = " REST api to Update a  structure details"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " Http status ok"
		)
	})
	@PutMapping("/updateStructureDetails/{id}")
	public String updateStructureDetailsById(@PathVariable long id, @Valid@RequestBody StructureAndDetails details) {
	    return propertyInsuranceService.updateStructureDetailsById(id, details);
	   
	}
	@Operation(
		summary = " updating a fil details related to property by Id ",
		description = " REST api to Update a  structure details"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " Http status ok"
		)
	})
	@PutMapping("/updateFillDetails/{id}")
	public String updateFillDetailsById(@PathVariable long id, @RequestBody FillDetails fillDetails) {

		return propertyInsuranceService.updateFillDetailsById(id, fillDetails);
		
	}
//	@PutMapping("/updateFillDetailsByCustomerId/{customerId}")
//	public String updateFillDetailsByCustomerId(@PathVariable String customerId, @RequestBody FillDetails fillDetails) {
//
//		return propertyInsuranceService.updateFillDetailsByCustomerId(customerId, fillDetails);
//		
//	}
	@Operation(
		summary = " updating a fil details related to property by Id ",
		description = " REST api to Update a  structure details"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " Http status ok"
		)
	})
	@PutMapping("/updateFillDetailsPageById/{id}")
	public String updateFillDetailsPageById(@PathVariable long id,@Valid @RequestBody FillDetails fillDetails) {

		return propertyInsuranceService.updateFillDetailsPageById(id, fillDetails);
		
	}
	@Operation(
		summary = " updating a payment details by Id ",
		description = " REST api to Update a payment details"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " Http status ok"
		)
	})
	@PutMapping("/updatePaymentDetails/{id}")
	public String updatePaymentDetailsById(@PathVariable long id,@Valid @RequestBody CustomerPaymentDetails paymentDetails) {
	    return propertyInsuranceService.updatePaymentDetailsById(id, paymentDetails);
	    
	}
	@Operation(
		summary = " updating a customer details by Id ",
		description = " REST api to Update a customer details"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " Http status ok"
		)
	})
	@PutMapping("/updateCustomer/{id}")
	public String updateCustomerById(@PathVariable long id,@Valid @RequestBody CustomerSignup customer) {
	    return propertyInsuranceService.updateCustomerById(id, customer);
	   
	}
	@Operation(
		summary = " updating a customer details by mobileNumber ",
		description = " REST api to Update a customer details"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " Http status ok"
		)
	})
	@PutMapping("/updateCustomerByMobileNo/{id}/{mobileno}")
	public String updateCustomerByMobileNo(@Valid @PathVariable Long id, @PathVariable String mobileno) {
	    return propertyInsuranceService. updateCustomerByMobileNo(id, mobileno);
	   
	}
	@Operation(
		summary = " updating a customer details by email ",
		description = " REST api to Update a customer details"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " Http status ok"
		)
	})
	@PutMapping("/updateCustomerByEmailId/{id}/{emailId}")
	public String updateCustomerByEmailId(@Valid @PathVariable long id, @PathVariable String emailId ) {
	    return propertyInsuranceService.  updateCustomerByEmailId(id, emailId);
	   
	}
	// @Operation(
	// 	summary = " updating a customer password by Id ",
	// 	description = " REST api to Update a customer password"
	// )
	// @ApiResponses({
	// 	@ApiResponse(
	// 		responseCode = "200",
	// 		description = " Http status ok"
	// 	)
	// })
	// @PutMapping("/updateCustomerPasswordByid/{id}")
    // public String updateCustomerPasswordByid(@PathVariable long id,@Valid @RequestBody CustomerSignup customer) {
    //     return  propertyInsuranceService.updateCustomerPasswordByid(id, customer);
	    
    // }
	
//	@PostMapping("/sendSms")
//    public ResponseEntity<String> sendSms(@RequestParam String mobileno) {
//        return (ResponseEntity<String>) propertyInsuranceService.sendSms(mobileno);
//    }
	@Operation(
		summary = " Getting sms for a mobile Number ",
		description = " REST api to Get the SMS for the mobileNumber"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " Http status ok"
		)
	})
	@GetMapping("/sendSms/{mobileno}")
    public String sendSms(@Pattern(regexp = "(^[6-9][0-9]{9}$)", message = "mobileNumber must be start with 6 to 9 and have 10 digits")@PathVariable String mobileno) 
	{
        return propertyInsuranceService.sendSms(mobileno);
    }
	@Operation(
		summary = " Getting currentDateTime ",
		description = " REST api to Get the currentDateTime "
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = " Http status ok"
		)
	})
	@GetMapping("/currentDateTime")
    public String getCurrentDateTime() {
		return propertyInsuranceService.getCurrentDateTime();
    }

//	@GetMapping("/otp")
//    public String generateOTP() 
//	{
//        LocalDateTime date = LocalDateTime.now();
//        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//        String formattedDateString = date.format(formattedDate);
//        String otp = formattedDateString.substring(formattedDateString.length() - 4);
//        return otp;
//    }
	


// for signup email otp
@PostMapping("/send/{emailRequest}")
public String sendEmail(@PathVariable String emailRequest) 
{
	return propertyInsuranceService.sendEmail(emailRequest);

}

// for updation email otp

@PostMapping("/sendEmailOTPforUpdation/{toEmail}")
public String EmailUpdation(@PathVariable String toEmail) 
{
	return propertyInsuranceService.EmailUpdation(toEmail);

}
// for emailsending at quotepage
@PostMapping("/post/emailQuotePage/{toEmail}")
	public String emailQuotePage(@PathVariable String toEmail,@RequestBody EmailQuotePageEntity eqpEntity)
	{
		return propertyInsuranceService.sendEmailQuotePage(toEmail, eqpEntity);
	}



	@Autowired
	private InvoiceService invoiceService;

		// to create Invoice
	@GetMapping("/create/{paymentId}")
	public void createPdf(HttpServletResponse response, @PathVariable String paymentId) {
	        // Setting content type and response headers
		response.setContentType("application/pdf");
	    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=RS_PI_invoice.pdf");

	     try {
	            invoiceService.export(response, paymentId);
	   		 } catch (DocumentException | IOException e) {
	            e.printStackTrace();
	      }
	    }

  	//to send an email to user at quotepage
	@PostMapping("/emailQuotePageTabularFormate/{toEmail}")
	public String emailQuotePageTabularFormate(@PathVariable String toEmail,@RequestBody QuoteDataTabularFormate  qdtf)
	{
		return propertyInsuranceService.sendEmailQuotePageTabularFormate(toEmail, qdtf);
	}


	
@PostMapping("/putStructure/Details1")
public ResponseEntity<Object> createDetails2(@Valid @RequestBody FillDetails request, BindingResult bindingResult) {
    // if (bindingResult.hasErrors()) {
    // 	System.out.println("binding results");
    //     Map<String, String> fieldErrors = bindingResult.getFieldErrors().stream()
    //             .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    //     return ResponseEntity.badRequest().body(fieldErrors);
    // }
    	String eligibilityCheck = propertyInsuranceService.checkEligibility1(request);
        System.out.println(eligibilityCheck);
        if (eligibilityCheck == "Records are present") {
        	System.out.println("eligibity results");
            return ResponseEntity.status(409).body("You are not eligible for this policy as the record already exists with the provided details");
        }
        else
        {
        	return ResponseEntity.ok().body("StructureAndDetails created successfully");
        }

		
	
}


	@GetMapping("/findByMobilenumber/details/{mobileno}")
		public CustomerDto getCustomerDetails(@PathVariable String mobileno) 
		{
			return propertyInsuranceService.findByMobileNumber(mobileno);
		}

}
