package com.cges.assetmng.repository;

import com.cges.assetmng.domain.ElementStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ElementStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElementStatusRepository extends JpaRepository<ElementStatus, Long> {

}
