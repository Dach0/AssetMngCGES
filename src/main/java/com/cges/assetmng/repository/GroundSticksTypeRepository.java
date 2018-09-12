package com.cges.assetmng.repository;

import com.cges.assetmng.domain.GroundSticksType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GroundSticksType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroundSticksTypeRepository extends JpaRepository<GroundSticksType, Long> {

}
