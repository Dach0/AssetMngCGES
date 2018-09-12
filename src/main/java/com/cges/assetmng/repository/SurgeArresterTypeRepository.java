package com.cges.assetmng.repository;

import com.cges.assetmng.domain.SurgeArresterType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SurgeArresterType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SurgeArresterTypeRepository extends JpaRepository<SurgeArresterType, Long> {

}
