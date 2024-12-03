package com.insurance.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.www.model.NotificationHystoryForEmail;

@Repository
public interface NotificationHystoryForEmailRepository extends JpaRepository<NotificationHystoryForEmail, Long> {

}
