package com.cges.assetmng.repository;

import com.cges.assetmng.domain.Substation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Substation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubstationRepository extends JpaRepository<Substation, Long> {

}
