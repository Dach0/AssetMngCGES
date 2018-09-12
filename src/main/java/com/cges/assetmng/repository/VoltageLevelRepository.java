package com.cges.assetmng.repository;

import com.cges.assetmng.domain.VoltageLevel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VoltageLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VoltageLevelRepository extends JpaRepository<VoltageLevel, Long> {

}
