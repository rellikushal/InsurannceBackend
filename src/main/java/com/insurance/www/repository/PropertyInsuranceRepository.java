package com.insurance.www.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.www.model.StructureAndDetails;

@Repository
public interface PropertyInsuranceRepository extends JpaRepository<StructureAndDetails, Long>
{

	Optional<List<StructureAndDetails>> findByCustomerId(String customerId);

	List<StructureAndDetails> findByPaymentId(String paymentId);
	
}

