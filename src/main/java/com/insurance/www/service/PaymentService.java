package com.insurance.www.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.www.model.Payment;
import com.insurance.www.repository.PaymentRepository;



@Service
public class PaymentService 
{
	
	@Autowired
	PaymentRepository paymentRepository;
	
	public Payment addData(Payment profile)
	{
		profile.setStartdate(LocalDate.now());
		profile.setEnddate(profile.getStartdate().plusYears(1).minusDays(1));
		return paymentRepository.save(profile);
	}
	
	public List<Payment> fetchAll()
	{
		return paymentRepository.findAll();
	}
	
	public List<Payment> fetch(String nmbr)
	{
		return paymentRepository.findByCustomerid(nmbr);
	}

	public List<Payment> fetchPolocy(String vnumber) {
		
		return paymentRepository.findByVnumber(vnumber);
	}
}

