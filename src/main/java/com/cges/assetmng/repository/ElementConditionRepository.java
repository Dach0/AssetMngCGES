package com.cges.assetmng.repository;

import com.cges.assetmng.domain.ElementCondition;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ElementCondition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElementConditionRepository extends JpaRepository<ElementCondition, Long> {

}
