package com.insurance.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.www.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>
{

	Customer findByMobile(String mobile);
	
	//Customer findByCustomerid(long customerid);

	List<Customer> findByCustomerid(String customerid);

	Customer findByEmail(String email);
}
