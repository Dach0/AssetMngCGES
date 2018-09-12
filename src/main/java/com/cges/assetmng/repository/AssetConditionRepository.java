package com.cges.assetmng.repository;

import com.cges.assetmng.domain.AssetCondition;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AssetCondition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetConditionRepository extends JpaRepository<AssetCondition, Long> {

}
