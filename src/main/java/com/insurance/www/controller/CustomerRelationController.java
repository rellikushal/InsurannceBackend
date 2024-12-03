package com.insurance.www.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.www.model.CustomerRelation;
import com.insurance.www.service.CustomerRelationService;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/customer/relations")
@CrossOrigin(origins="*")
public class CustomerRelationController {

    @Autowired
    private CustomerRelationService service;

    @GetMapping
    public List<CustomerRelation> getAllCustomerRelations() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerRelation> getCustomerRelationById(@PathVariable Long id) {
        Optional<CustomerRelation> customerRelation = service.findById(id);
        return customerRelation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/findBy{customerId}")
    public List<CustomerRelation> getCustomerRelationByCustomerID(@PathVariable String customerId ) {
        List<CustomerRelation> customerRelation = service.findByCustomerId(customerId);
        return customerRelation;
    }

    @PostMapping
    public ResponseEntity<CustomerRelation> createCustomerRelation(@RequestBody CustomerRelation customerRelation) {
        CustomerRelation savedCustomerRelation = service.save(customerRelation);
        return new ResponseEntity<>(savedCustomerRelation, HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<CustomerRelation> updateCustomerRelation(@PathVariable Long id, @RequestBody CustomerRelation customerRelation) {
//        if (!service.findById(id).isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        customerRelation.setId(id);
//        CustomerRelation updatedCustomerRelation = service.save(customerRelation);
//        return ResponseEntity.ok(updatedCustomerRelation);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerRelation(@PathVariable Long id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/check/{customerId}")
    public String checkRelationOfTheCustomer(@PathVariable String customerId,@RequestBody CustomerRelation customerRelation)
    {
    	return service.findByRelation(customerId,customerRelation);
    }
}
