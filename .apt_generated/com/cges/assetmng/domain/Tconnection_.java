package com.cges.assetmng.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tconnection.class)
public abstract class Tconnection_ {

	public static volatile SingularAttribute<Tconnection, String> tConnSubStationName;
	public static volatile SingularAttribute<Tconnection, Long> id;
	public static volatile SingularAttribute<Tconnection, Substation> substation2;
	public static volatile SingularAttribute<Tconnection, Substation> substation1;

}

