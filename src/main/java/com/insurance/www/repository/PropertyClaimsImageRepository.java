package com.insurance.www.repository;

import com.insurance.www.model.PropertyClaimsImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface PropertyClaimsImageRepository extends JpaRepository<PropertyClaimsImage,Long> {

    Optional<PropertyClaimsImage> findById(Long id);
    
}
