package com.cges.assetmng.repository;

import com.cges.assetmng.domain.CircuitBreakerType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CircuitBreakerType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CircuitBreakerTypeRepository extends JpaRepository<CircuitBreakerType, Long> {

}
