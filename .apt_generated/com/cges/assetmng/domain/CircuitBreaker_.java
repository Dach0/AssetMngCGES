package com.cges.assetmng.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CircuitBreaker.class)
public abstract class CircuitBreaker_ {

	public static volatile SingularAttribute<CircuitBreaker, Integer> current;
	public static volatile SingularAttribute<CircuitBreaker, Field> field;
	public static volatile SingularAttribute<CircuitBreaker, String> serial;
	public static volatile SingularAttribute<CircuitBreaker, Integer> shortCircuitCurrent;
	public static volatile SingularAttribute<CircuitBreaker, Substation> substation;
	public static volatile SingularAttribute<CircuitBreaker, Long> id;
	public static volatile SingularAttribute<CircuitBreaker, Integer> productionYear;
	public static volatile SingularAttribute<CircuitBreaker, CircuitBreakerDrive> drive;
	public static volatile SingularAttribute<CircuitBreaker, CircuitBreakerType> type;
	public static volatile SingularAttribute<CircuitBreaker, Manufacturer> manufactured;
	public static volatile SingularAttribute<CircuitBreaker, String> picture;

}

