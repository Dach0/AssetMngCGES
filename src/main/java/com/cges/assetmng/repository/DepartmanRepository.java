package com.cges.assetmng.repository;

import com.cges.assetmng.domain.Departman;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Departman entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepartmanRepository extends JpaRepository<Departman, Long> {

}
