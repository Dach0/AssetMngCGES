package com.cges.assetmng.repository;

import com.cges.assetmng.domain.TransmissionRatio;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TransmissionRatio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransmissionRatioRepository extends JpaRepository<TransmissionRatio, Long> {

}
