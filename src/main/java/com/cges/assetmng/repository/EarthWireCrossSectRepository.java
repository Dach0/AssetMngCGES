package com.cges.assetmng.repository;

import com.cges.assetmng.domain.EarthWireCrossSect;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EarthWireCrossSect entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EarthWireCrossSectRepository extends JpaRepository<EarthWireCrossSect, Long> {

}
