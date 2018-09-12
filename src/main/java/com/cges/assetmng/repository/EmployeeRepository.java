package com.cges.assetmng.repository;

import com.cges.assetmng.domain.Employee;
import com.cges.assetmng.service.dto.EmployeeInfoPieChartDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Employee entity.
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	//vraca mi vrijednosti za pieChart, prebrojava zaposlene po grupama!
	@Query (value = "select new com.cges.assetmng.service.dto.EmployeeInfoPieChartDTO(eg.groupName, Count(eg.id)) FROM Employee e "
			+ "LEFT JOIN e.group eg "
			+ "GROUP BY eg.groupName")
	Page<EmployeeInfoPieChartDTO> getEmplGroupedByGroup(Pageable pageable);
}
