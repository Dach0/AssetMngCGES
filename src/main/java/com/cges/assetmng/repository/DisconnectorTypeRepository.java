package com.cges.assetmng.repository;

import com.cges.assetmng.domain.DisconnectorType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DisconnectorType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DisconnectorTypeRepository extends JpaRepository<DisconnectorType, Long> {

}
