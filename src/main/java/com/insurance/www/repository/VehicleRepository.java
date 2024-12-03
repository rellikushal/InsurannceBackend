package com.insurance.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.www.model.Vehicle;



@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

	Vehicle findByVnumber(String vnumber);

}
