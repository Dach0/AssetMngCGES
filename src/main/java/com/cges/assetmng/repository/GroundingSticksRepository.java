package com.cges.assetmng.repository;

import com.cges.assetmng.domain.GroundingSticks;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GroundingSticks entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroundingSticksRepository extends JpaRepository<GroundingSticks, Long> {

}
