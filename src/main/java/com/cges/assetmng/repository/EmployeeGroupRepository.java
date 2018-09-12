package com.cges.assetmng.repository;

import java.util.List;

import com.cges.assetmng.domain.EmployeeGroup;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring Data  repository for the EmployeeGroup entity.
 */
@SuppressWarnings(value = { })
@Repository
public interface EmployeeGroupRepository extends JpaRepository<EmployeeGroup, Long> {
	
	@Transactional
	List<EmployeeGroup> deleteByGroupName(String gName);

}
