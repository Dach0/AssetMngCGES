package com.cges.assetmng.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Asset.class)
public abstract class Asset_ {

	public static volatile SingularAttribute<Asset, Double> marketPrice;
	public static volatile SingularAttribute<Asset, String> attachments;
	public static volatile SingularAttribute<Asset, String> notes;
	public static volatile SingularAttribute<Asset, String> serialNumber;
	public static volatile SingularAttribute<Asset, String> description;
	public static volatile SingularAttribute<Asset, Double> scrapPrice;
	public static volatile SingularAttribute<Asset, Double> purchasePrice;
	public static volatile SingularAttribute<Asset, Type> type;
	public static volatile SingularAttribute<Asset, String> picture;
	public static volatile SingularAttribute<Asset, String> manufacturer;
	public static volatile SingularAttribute<Asset, AssetCondition> condition;
	public static volatile SingularAttribute<Asset, LocalDate> dateOfObligation;
	public static volatile SingularAttribute<Asset, LocalDate> purchasedDate;
	public static volatile SingularAttribute<Asset, LocalDate> inServiceDate;
	public static volatile SingularAttribute<Asset, Location> location;
	public static volatile SingularAttribute<Asset, String> model;
	public static volatile SingularAttribute<Asset, Long> id;
	public static volatile SingularAttribute<Asset, Employee> asset;
	public static volatile SingularAttribute<Asset, String> brand;
	public static volatile SingularAttribute<Asset, LocalDate> warrenty;
	public static volatile SingularAttribute<Asset, AssetStatus> status;

}

