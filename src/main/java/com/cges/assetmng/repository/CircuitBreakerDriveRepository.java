package com.cges.assetmng.repository;

import com.cges.assetmng.domain.CircuitBreakerDrive;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CircuitBreakerDrive entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CircuitBreakerDriveRepository extends JpaRepository<CircuitBreakerDrive, Long> {

}
