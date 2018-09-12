package com.cges.assetmng.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Substation.class)
public abstract class Substation_ {

	public static volatile SingularAttribute<Substation, String> name;
	public static volatile SingularAttribute<Substation, Integer> operationYear;
	public static volatile SingularAttribute<Substation, Long> id;
	public static volatile SingularAttribute<Substation, Facility> facility;

}

