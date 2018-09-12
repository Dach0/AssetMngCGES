package com.cges.assetmng.repository;

import com.cges.assetmng.domain.TransformatorNumber;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TransformatorNumber entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransformatorNumberRepository extends JpaRepository<TransformatorNumber, Long> {

}
