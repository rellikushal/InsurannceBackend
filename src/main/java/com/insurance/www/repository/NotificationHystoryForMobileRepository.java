package com.insurance.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.www.model.NotificationHystoryFormobile;

@Repository
public interface NotificationHystoryForMobileRepository extends JpaRepository<NotificationHystoryFormobile, Long> {

}