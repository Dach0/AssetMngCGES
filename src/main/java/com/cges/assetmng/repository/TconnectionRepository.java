package com.cges.assetmng.repository;

import com.cges.assetmng.domain.Tconnection;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Tconnection entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TconnectionRepository extends JpaRepository<Tconnection, Long> {

}
