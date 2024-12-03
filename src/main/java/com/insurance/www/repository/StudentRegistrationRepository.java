package com.insurance.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.www.model.StudentRegistrationEntity;

public interface StudentRegistrationRepository extends JpaRepository<StudentRegistrationEntity,Long>
{

}
