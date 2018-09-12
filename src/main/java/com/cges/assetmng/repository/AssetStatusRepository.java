package com.cges.assetmng.repository;

import com.cges.assetmng.domain.AssetStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AssetStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetStatusRepository extends JpaRepository<AssetStatus, Long> {

}
