package com.cges.assetmng.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SingularAttribute<Employee, String> attachments;
	public static volatile SingularAttribute<Employee, String> notes;
	public static volatile SingularAttribute<Employee, PhonePrivilage> phPrivilage;
	public static volatile SingularAttribute<Employee, String> telNum2;
	public static volatile SingularAttribute<Employee, LocalDate> endDate;
	public static volatile SingularAttribute<Employee, String> telNum1;
	public static volatile SingularAttribute<Employee, Contract> contractType;
	public static volatile SingularAttribute<Employee, Departman> departman;
	public static volatile SingularAttribute<Employee, Job> jobDesc;
	public static volatile SingularAttribute<Employee, String> lastname;
	public static volatile SingularAttribute<Employee, Qualification> profQualification;
	public static volatile SingularAttribute<Employee, String> name;
	public static volatile SingularAttribute<Employee, Long> id;
	public static volatile SingularAttribute<Employee, String> pictureEmpl;
	public static volatile SingularAttribute<Employee, Sector> sector;
	public static volatile SingularAttribute<Employee, String> email;
	public static volatile SingularAttribute<Employee, LocalDate> startDate;
	public static volatile SingularAttribute<Employee, EmployeeGroup> group;
	public static volatile SingularAttribute<Employee, JobStatus> status;

}

