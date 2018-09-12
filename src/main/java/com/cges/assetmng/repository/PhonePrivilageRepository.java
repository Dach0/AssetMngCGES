package com.cges.assetmng.repository;

import com.cges.assetmng.domain.PhonePrivilage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PhonePrivilage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PhonePrivilageRepository extends JpaRepository<PhonePrivilage, Long> {

}
