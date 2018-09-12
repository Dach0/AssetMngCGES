package com.cges.assetmng.repository;

import com.cges.assetmng.domain.Coupling;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Coupling entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CouplingRepository extends JpaRepository<Coupling, Long> {

}
