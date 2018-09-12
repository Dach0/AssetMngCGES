package com.cges.assetmng.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BoardOfDirectors.class)
public abstract class BoardOfDirectors_ {

	public static volatile SetAttribute<BoardOfDirectors, BoardMember> member3S;
	public static volatile SetAttribute<BoardOfDirectors, BoardMember> member2S;
	public static volatile SetAttribute<BoardOfDirectors, BoardMember> member5S;
	public static volatile SetAttribute<BoardOfDirectors, BoardMember> executives;
	public static volatile SetAttribute<BoardOfDirectors, BoardMember> member4S;
	public static volatile SingularAttribute<BoardOfDirectors, LocalDate> endDate;
	public static volatile SetAttribute<BoardOfDirectors, BoardMember> member1S;
	public static volatile SetAttribute<BoardOfDirectors, BoardMember> execAssistents;
	public static volatile SingularAttribute<BoardOfDirectors, Long> id;
	public static volatile SingularAttribute<BoardOfDirectors, LocalDate> startDate;

}

