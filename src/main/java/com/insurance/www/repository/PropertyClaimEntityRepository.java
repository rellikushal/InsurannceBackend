package com.insurance.www.repository;

import com.insurance.www.model.PropertyClaimEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyClaimEntityRepository extends JpaRepository<PropertyClaimEntity,Long> {


    @EntityGraph(attributePaths = "images")
    List<PropertyClaimEntity> findAll();

    @EntityGraph(attributePaths = "images")
    Optional<PropertyClaimEntity> findById(Long id);

    @Query("SELECT DISTINCT p FROM PropertyClaimEntity p JOIN FETCH p.images i WHERE i.imageStatus = 'APPLIED' OR i.imageStatus = 'APPROVED'")
    List<PropertyClaimEntity> findAllWithApprovedOrAppliedImages();

    @Query("SELECT p FROM PropertyClaimEntity p LEFT JOIN FETCH p.images i WHERE p.id = :id AND (i.imageStatus = 'APPLIED' OR i.imageStatus = 'APPROVED')")
    Optional<PropertyClaimEntity> findByIdWithApprovedOrAppliedImages(@Param("id") Long id);
    
}
