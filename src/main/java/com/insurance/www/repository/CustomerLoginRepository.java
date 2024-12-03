package com.insurance.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.www.model.CustomerLogin;

@Repository
public interface CustomerLoginRepository extends JpaRepository<CustomerLogin , Long>
{

}
