package com.cges.assetmng.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SurgeArrester.class)
public abstract class SurgeArrester_ {

	public static volatile SingularAttribute<SurgeArrester, Field> field;
	public static volatile SingularAttribute<SurgeArrester, Integer> drainageCurrent;
	public static volatile SingularAttribute<SurgeArrester, String> serial;
	public static volatile SingularAttribute<SurgeArrester, Substation> substation;
	public static volatile SingularAttribute<SurgeArrester, Long> id;
	public static volatile SingularAttribute<SurgeArrester, Integer> productionYear;
	public static volatile SingularAttribute<SurgeArrester, SurgeArresterType> type;
	public static volatile SingularAttribute<SurgeArrester, Manufacturer> manufactured;
	public static volatile SingularAttribute<SurgeArrester, String> ucur;
	public static volatile SingularAttribute<SurgeArrester, String> picture;

}

