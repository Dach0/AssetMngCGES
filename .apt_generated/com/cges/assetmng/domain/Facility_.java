package com.cges.assetmng.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Facility.class)
public abstract class Facility_ {

	public static volatile SingularAttribute<Facility, Integer> inOperationSince;
	public static volatile SingularAttribute<Facility, VoltageLevel> voltageLevel;
	public static volatile SingularAttribute<Facility, Integer> numOfTransformers;
	public static volatile SingularAttribute<Facility, Long> id;
	public static volatile SingularAttribute<Facility, Integer> power;
	public static volatile SingularAttribute<Facility, String> busbars;
	public static volatile SetAttribute<Facility, Substation> substations;
	public static volatile SingularAttribute<Facility, FacilityMaintainingCo> maintaining;
	public static volatile SingularAttribute<Facility, Integer> numOfFields;

}

