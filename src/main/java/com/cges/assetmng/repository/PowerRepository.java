package com.cges.assetmng.repository;

import com.cges.assetmng.domain.Power;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Power entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PowerRepository extends JpaRepository<Power, Long> {

}
