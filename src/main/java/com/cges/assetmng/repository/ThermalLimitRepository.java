package com.cges.assetmng.repository;

import com.cges.assetmng.domain.ThermalLimit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ThermalLimit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThermalLimitRepository extends JpaRepository<ThermalLimit, Long> {

}
