package com.cges.assetmng.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ThermalLimit.class)
public abstract class ThermalLimit_ {

	public static volatile SingularAttribute<ThermalLimit, Integer> iMaxSummer;
	public static volatile SingularAttribute<ThermalLimit, Integer> iMaxWinter;
	public static volatile SingularAttribute<ThermalLimit, String> templateName;
	public static volatile SingularAttribute<ThermalLimit, Integer> thermalLimit;
	public static volatile SingularAttribute<ThermalLimit, Integer> pMaxSummer;
	public static volatile SingularAttribute<ThermalLimit, Long> id;
	public static volatile SingularAttribute<ThermalLimit, Integer> pMaxWinter;

}

