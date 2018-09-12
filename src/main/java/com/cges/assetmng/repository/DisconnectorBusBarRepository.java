package com.cges.assetmng.repository;

import com.cges.assetmng.domain.DisconnectorBusBar;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DisconnectorBusBar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DisconnectorBusBarRepository extends JpaRepository<DisconnectorBusBar, Long> {

}
