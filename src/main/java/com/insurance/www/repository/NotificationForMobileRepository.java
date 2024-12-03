package com.insurance.www.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.www.model.NotificationForMobile;

@Repository
public interface NotificationForMobileRepository extends JpaRepository<NotificationForMobile, Long> {

	List<NotificationForMobile> findByStatus(String status);

	Optional<NotificationForMobile> findByCustomerIdAndStatus(String customerId , String status);


}
