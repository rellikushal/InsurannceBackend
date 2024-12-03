package com.insurance.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.www.model.PremiumCaliculation;

@Repository
public interface PremiumCaliculationRepository extends JpaRepository <PremiumCaliculation,Long>
{

    PremiumCaliculation findByStateTrue();
        
}
