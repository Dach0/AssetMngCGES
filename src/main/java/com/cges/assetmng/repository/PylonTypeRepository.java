package com.cges.assetmng.repository;

import com.cges.assetmng.domain.PylonType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PylonType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PylonTypeRepository extends JpaRepository<PylonType, Long> {

}
