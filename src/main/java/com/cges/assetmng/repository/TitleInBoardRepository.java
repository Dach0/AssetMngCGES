package com.cges.assetmng.repository;

import com.cges.assetmng.domain.TitleInBoard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TitleInBoard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TitleInBoardRepository extends JpaRepository<TitleInBoard, Long> {

}
