package com.cges.assetmng.repository;

import com.cges.assetmng.domain.ServiceCompany;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ServiceCompany entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceCompanyRepository extends JpaRepository<ServiceCompany, Long> {

}
