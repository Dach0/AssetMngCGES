package com.cges.assetmng.repository;

import com.cges.assetmng.domain.CorrectionFactor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CorrectionFactor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CorrectionFactorRepository extends JpaRepository<CorrectionFactor, Long> {

}
