package com.cges.assetmng.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CurrentMeasuringTransformer.class)
public abstract class CurrentMeasuringTransformer_ {

	public static volatile SingularAttribute<CurrentMeasuringTransformer, Integer> nominalPower;
	public static volatile SingularAttribute<CurrentMeasuringTransformer, Double> accuracyClass;
	public static volatile SingularAttribute<CurrentMeasuringTransformer, Field> field;
	public static volatile SingularAttribute<CurrentMeasuringTransformer, String> serial;
	public static volatile SingularAttribute<CurrentMeasuringTransformer, Substation> substation;
	public static volatile SingularAttribute<CurrentMeasuringTransformer, Long> id;
	public static volatile SingularAttribute<CurrentMeasuringTransformer, Integer> productionYear;
	public static volatile SingularAttribute<CurrentMeasuringTransformer, String> transmissionRatio;
	public static volatile SingularAttribute<CurrentMeasuringTransformer, CmtType> type;
	public static volatile SingularAttribute<CurrentMeasuringTransformer, Manufacturer> manufactured;
	public static volatile SingularAttribute<CurrentMeasuringTransformer, String> picture;

}

