package com.cges.assetmng.repository;

import com.cges.assetmng.domain.TransformerType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TransformerType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransformerTypeRepository extends JpaRepository<TransformerType, Long> {

}
