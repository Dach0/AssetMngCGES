package com.cges.assetmng.repository;

import com.cges.assetmng.domain.CircuitBreaker;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CircuitBreaker entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CircuitBreakerRepository extends JpaRepository<CircuitBreaker, Long> {

}
