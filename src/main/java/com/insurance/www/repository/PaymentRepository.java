package com.insurance.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.www.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>
{
	List<Payment> findByVnumber(String vnumber);
	
	List<Payment> findAllByVnumber(String vnumber);
	
	List<Payment> findByCustomerid(String customerid);
	
	List<Payment> findByPaymentid(String paymentid);
}
