package com.cges.assetmng.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VoltageMeasuringTransformer.class)
public abstract class VoltageMeasuringTransformer_ {

	public static volatile SingularAttribute<VoltageMeasuringTransformer, Integer> nominalPower;
	public static volatile SingularAttribute<VoltageMeasuringTransformer, Double> accuracyClass;
	public static volatile SingularAttribute<VoltageMeasuringTransformer, Field> field;
	public static volatile SingularAttribute<VoltageMeasuringTransformer, String> serial;
	public static volatile SingularAttribute<VoltageMeasuringTransformer, Substation> substation;
	public static volatile SingularAttribute<VoltageMeasuringTransformer, Long> id;
	public static volatile SingularAttribute<VoltageMeasuringTransformer, Integer> productionYear;
	public static volatile SingularAttribute<VoltageMeasuringTransformer, String> transmissionRatio;
	public static volatile SingularAttribute<VoltageMeasuringTransformer, VmtType> type;
	public static volatile SingularAttribute<VoltageMeasuringTransformer, Manufacturer> manufactured;
	public static volatile SingularAttribute<VoltageMeasuringTransformer, String> picture;

}

