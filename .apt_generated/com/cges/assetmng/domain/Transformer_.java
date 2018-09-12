package com.cges.assetmng.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Transformer.class)
public abstract class Transformer_ {

	public static volatile SingularAttribute<Transformer, String> serialNumber;
	public static volatile SingularAttribute<Transformer, Boolean> isEarthGrounding;
	public static volatile SingularAttribute<Transformer, Integer> productionYear;
	public static volatile SingularAttribute<Transformer, TransformatorNumber> redniBroj;
	public static volatile SingularAttribute<Transformer, TransformerType> type;
	public static volatile SingularAttribute<Transformer, TransmissionRatio> transRatio;
	public static volatile SingularAttribute<Transformer, String> picture;
	public static volatile SingularAttribute<Transformer, Manufacturer> manufacturer;
	public static volatile SingularAttribute<Transformer, Coupling> coupling;
	public static volatile SingularAttribute<Transformer, Double> shortCircuitVoltage;
	public static volatile SingularAttribute<Transformer, ElementCondition> condition;
	public static volatile SingularAttribute<Transformer, Service> service;
	public static volatile SingularAttribute<Transformer, Substation> substation;
	public static volatile SingularAttribute<Transformer, Long> id;
	public static volatile SingularAttribute<Transformer, Power> power;
	public static volatile SingularAttribute<Transformer, Integer> installationYear;
	public static volatile SingularAttribute<Transformer, ElementStatus> status;

}

