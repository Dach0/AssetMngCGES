package com.cges.assetmng.repository;

import com.cges.assetmng.domain.CmtType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CmtType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CmtTypeRepository extends JpaRepository<CmtType, Long> {

}
