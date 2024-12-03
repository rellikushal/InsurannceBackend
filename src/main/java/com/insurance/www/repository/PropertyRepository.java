package com.insurance.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.www.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>{

	List<Property> findByCustomerId(String customerId);

}
