package com.insurance.www.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.www.model.EmailQuotePageEntity;

public interface EmailQuotePageRepo extends JpaRepository<EmailQuotePageEntity, Long> {
    EmailQuotePageEntity findByUniqueId(String uniqueId);

	//  List<EmailQuotePageEntity> findByGuestId(String guestId);

	List<EmailQuotePageEntity> findByCreateDateBefore(LocalDateTime oneMonthAgoDateTime);
}
