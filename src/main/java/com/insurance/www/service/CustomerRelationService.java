package com.insurance.www.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.insurance.www.model.CustomerRelation;
import com.insurance.www.repository.CustomerRelationRepository;

@Service
public class CustomerRelationService {

    @Autowired
    private CustomerRelationRepository repository;

    public List<CustomerRelation> findAll() {
        return repository.findAll();
    }

    public Optional<CustomerRelation> findById(Long id) {
        return repository.findById(id);
    }
    public List<CustomerRelation> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    public CustomerRelation save(CustomerRelation customerRelation) {
        return repository.save(customerRelation);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    public String findByRelation(String customerId,CustomerRelation customerRelation)
    {
    	String relation = customerRelation.getRelation();
    	Optional<CustomerRelation> data = repository.findByCustomerIdAndRelation(customerId,relation);
    	if(data.isPresent())
    	{
    		return "Data Found";
    	}
    	else
    	{
    		return "Data Not Found";
    	}
    }

	
}
