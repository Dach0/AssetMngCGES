package com.cges.assetmng.repository;

import com.cges.assetmng.domain.CurrentMeasuringTransformer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CurrentMeasuringTransformer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CurrentMeasuringTransformerRepository extends JpaRepository<CurrentMeasuringTransformer, Long> {

}
