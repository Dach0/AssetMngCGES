package com.cges.assetmng.repository;

import com.cges.assetmng.domain.DisconnectorDrive;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DisconnectorDrive entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DisconnectorDriveRepository extends JpaRepository<DisconnectorDrive, Long> {

}
