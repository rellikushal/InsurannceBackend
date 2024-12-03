package com.insurance.www.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.insurance.www.model.Payment;
import com.insurance.www.service.PaymentService;
import com.insurance.www.service.VehicleInvoiceService;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/payment")
public class PaymentController 
{
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	VehicleInvoiceService invoiceService;
	
	@PostMapping("/add")
	public Payment addData(@RequestBody Payment profile)
	{
		return paymentService.addData(profile);
	}
	
	@GetMapping("/fetch")
	public List<Payment> fetchAll()
	{
		return paymentService.fetchAll();
	}
	
	@GetMapping("/get/{id}")
	public List<Payment> fetch(@PathVariable String id)
	{
		return paymentService.fetch(id);
	}

	@GetMapping("/fetchPolocyByVnumber/{vnumber}")
	public List<Payment> fetchPolocy(@PathVariable String vnumber)
	{
		return paymentService.fetchPolocy(vnumber);
	}
	
	@GetMapping("/create")
    public void createPdf(HttpServletResponse response, @RequestParam String paymentid, @RequestParam String customerid) {
        // Setting content type and response headers
        response.setContentType("application/pdf");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=RSVIInvoice.pdf");

        try {
        	invoiceService.export(response, paymentid, customerid);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
