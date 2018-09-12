package com.cges.assetmng.repository;

import com.cges.assetmng.domain.BoardOfDirectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the BoardOfDirectors entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BoardOfDirectorsRepository extends JpaRepository<BoardOfDirectors, Long> {

    @Query(value = "select distinct board_of_directors from BoardOfDirectors board_of_directors left join fetch board_of_directors.executives left join fetch board_of_directors.execAssistents left join fetch board_of_directors.member1S left join fetch board_of_directors.member2S left join fetch board_of_directors.member3S left join fetch board_of_directors.member4S left join fetch board_of_directors.member5S",
        countQuery = "select count(distinct board_of_directors) from BoardOfDirectors board_of_directors")
    Page<BoardOfDirectors> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct board_of_directors from BoardOfDirectors board_of_directors left join fetch board_of_directors.executives left join fetch board_of_directors.execAssistents left join fetch board_of_directors.member1S left join fetch board_of_directors.member2S left join fetch board_of_directors.member3S left join fetch board_of_directors.member4S left join fetch board_of_directors.member5S")
    List<BoardOfDirectors> findAllWithEagerRelationships();

    @Query("select board_of_directors from BoardOfDirectors board_of_directors left join fetch board_of_directors.executives left join fetch board_of_directors.execAssistents left join fetch board_of_directors.member1S left join fetch board_of_directors.member2S left join fetch board_of_directors.member3S left join fetch board_of_directors.member4S left join fetch board_of_directors.member5S where board_of_directors.id =:id")
    Optional<BoardOfDirectors> findOneWithEagerRelationships(@Param("id") Long id);

}
