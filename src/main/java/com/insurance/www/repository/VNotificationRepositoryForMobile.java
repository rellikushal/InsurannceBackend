package com.insurance.www.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.www.model.VNotificationForMobile;


@Repository
public interface VNotificationRepositoryForMobile extends JpaRepository<VNotificationForMobile, Long>
{

	List<VNotificationForMobile> findByStatus(String status);

	Optional<VNotificationForMobile> findByCustomeridAndStatus(String customerid, String status);

}
