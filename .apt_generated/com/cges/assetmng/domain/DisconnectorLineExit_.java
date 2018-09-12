package com.cges.assetmng.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DisconnectorLineExit.class)
public abstract class DisconnectorLineExit_ {

	public static volatile SingularAttribute<DisconnectorLineExit, Integer> current;
	public static volatile SingularAttribute<DisconnectorLineExit, Field> field;
	public static volatile SingularAttribute<DisconnectorLineExit, String> serial;
	public static volatile SingularAttribute<DisconnectorLineExit, Substation> substation;
	public static volatile SingularAttribute<DisconnectorLineExit, Long> id;
	public static volatile SingularAttribute<DisconnectorLineExit, Integer> productionYear;
	public static volatile SingularAttribute<DisconnectorLineExit, DisconnectorDrive> drive;
	public static volatile SingularAttribute<DisconnectorLineExit, DisconnectorType> type;
	public static volatile SingularAttribute<DisconnectorLineExit, Manufacturer> manufactured;
	public static volatile SingularAttribute<DisconnectorLineExit, String> picture;

}

