package com.cges.assetmng.repository;

import com.cges.assetmng.domain.FacilityMaintainingCo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FacilityMaintainingCo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FacilityMaintainingCoRepository extends JpaRepository<FacilityMaintainingCo, Long> {

}
