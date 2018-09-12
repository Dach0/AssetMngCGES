package com.cges.assetmng.repository;

import com.cges.assetmng.domain.DisconnectorLineExit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DisconnectorLineExit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DisconnectorLineExitRepository extends JpaRepository<DisconnectorLineExit, Long> {

}
