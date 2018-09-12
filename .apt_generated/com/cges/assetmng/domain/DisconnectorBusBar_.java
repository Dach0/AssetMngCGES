package com.cges.assetmng.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DisconnectorBusBar.class)
public abstract class DisconnectorBusBar_ {

	public static volatile SingularAttribute<DisconnectorBusBar, Integer> current;
	public static volatile SingularAttribute<DisconnectorBusBar, Field> field;
	public static volatile SingularAttribute<DisconnectorBusBar, String> serial;
	public static volatile SingularAttribute<DisconnectorBusBar, Substation> substation;
	public static volatile SingularAttribute<DisconnectorBusBar, Long> id;
	public static volatile SingularAttribute<DisconnectorBusBar, Integer> productionYear;
	public static volatile SingularAttribute<DisconnectorBusBar, DisconnectorDrive> drive;
	public static volatile SingularAttribute<DisconnectorBusBar, DisconnectorType> type;
	public static volatile SingularAttribute<DisconnectorBusBar, Manufacturer> manufactured;
	public static volatile SingularAttribute<DisconnectorBusBar, String> picture;

}

