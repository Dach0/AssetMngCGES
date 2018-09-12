package com.cges.assetmng.repository;

import com.cges.assetmng.domain.BoardMember;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BoardMember entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BoardMemberRepository extends JpaRepository<BoardMember, Long> {

}
