package com.cges.assetmng.repository;

import com.cges.assetmng.domain.GroundSticksDrive;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GroundSticksDrive entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroundSticksDriveRepository extends JpaRepository<GroundSticksDrive, Long> {

}
