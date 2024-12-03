package com.insurance.www.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.www.model.VNotificationForEmail;


@Repository
public interface VNotificationRepositoryForEmail extends JpaRepository<VNotificationForEmail, Long>
{

	List<VNotificationForEmail> findByStatus(String status);

	Optional<VNotificationForEmail> findByCustomeridAndStatus(String customerid, String status);

}

