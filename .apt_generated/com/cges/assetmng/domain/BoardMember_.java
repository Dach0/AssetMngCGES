package com.cges.assetmng.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BoardMember.class)
public abstract class BoardMember_ {

	public static volatile SingularAttribute<BoardMember, String> lastName;
	public static volatile SingularAttribute<BoardMember, Qualification> qualification;
	public static volatile SingularAttribute<BoardMember, TitleInBoard> boardTitle;
	public static volatile SingularAttribute<BoardMember, String> phone;
	public static volatile SingularAttribute<BoardMember, String> name;
	public static volatile SingularAttribute<BoardMember, Long> id;
	public static volatile SingularAttribute<BoardMember, String> email;
	public static volatile SingularAttribute<BoardMember, String> picture;

}

