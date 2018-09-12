package com.cges.assetmng.repository;

import com.cges.assetmng.domain.VmtType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VmtType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VmtTypeRepository extends JpaRepository<VmtType, Long> {

}
