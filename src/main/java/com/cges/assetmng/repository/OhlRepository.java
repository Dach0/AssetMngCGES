package com.cges.assetmng.repository;

import com.cges.assetmng.domain.Ohl;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Ohl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OhlRepository extends JpaRepository<Ohl, Long> {

}
