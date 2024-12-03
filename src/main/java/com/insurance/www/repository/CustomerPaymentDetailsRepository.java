package com.insurance.www.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.www.model.CustomerPaymentDetails;

@Repository
public interface CustomerPaymentDetailsRepository extends JpaRepository<CustomerPaymentDetails , Long>
{

	List<CustomerPaymentDetails> findByCustomerId(String customerId);

    List<CustomerPaymentDetails> findByPaymentId(String paymentId);


	

}
