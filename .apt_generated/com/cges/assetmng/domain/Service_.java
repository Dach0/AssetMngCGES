package com.cges.assetmng.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Service.class)
public abstract class Service_ {

	public static volatile SingularAttribute<Service, Employee> doneByEmpl;
	public static volatile SetAttribute<Service, Transformer> transformers;
	public static volatile SingularAttribute<Service, String> attachments;
	public static volatile SingularAttribute<Service, String> notes;
	public static volatile SingularAttribute<Service, ServiceCompany> doneByComp;
	public static volatile SingularAttribute<Service, LocalDate> servicedTo;
	public static volatile SingularAttribute<Service, Double> sparePartsPrice;
	public static volatile SingularAttribute<Service, String> serviceDescription;
	public static volatile SingularAttribute<Service, Long> id;
	public static volatile SingularAttribute<Service, Double> repairPrice;
	public static volatile SingularAttribute<Service, ServiceType> type;
	public static volatile SingularAttribute<Service, LocalDate> servicedFrom;

}

