package com.insurance.www.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.www.model.CustomerRelation;

public interface CustomerRelationRepository extends JpaRepository<CustomerRelation, Long> {

	List<CustomerRelation> findByCustomerId(String customerId);
	
	Optional<CustomerRelation> findByCustomerIdAndRelation(String customerId,String relation);
}