package com.cges.assetmng.service.dto;
/**
 * A DTO representing a group of emplooyees and ther count by groups
 */
public class EmployeeInfoPieChartDTO {

	private String groups;
	private Long countByGroup;
	
	
	
	public EmployeeInfoPieChartDTO() {
		super();
	}
	public EmployeeInfoPieChartDTO(String groups, Long countByGroup) {
		super();
		this.groups = groups;
		this.countByGroup = countByGroup;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	public Long getCountByGroup() {
		return countByGroup;
	}
	public void setCountByGroup(Long countByGroup) {
		this.countByGroup = countByGroup;
	}
	
	
}
