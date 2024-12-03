package com.insurance.www.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.www.model.NotificationForEmail;

@Repository
public interface NotificationForEmailRepository extends JpaRepository<NotificationForEmail, Long> {

	List<NotificationForEmail> findByStatus(String status);

	Optional<NotificationForEmail> findByCustomerIdAndStatus(String customerId,String status) ;

}