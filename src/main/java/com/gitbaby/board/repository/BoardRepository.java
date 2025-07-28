package com.gitbaby.board.repository;

import com.gitbaby.board.domain.entity.Board;
import com.gitbaby.board.domain.projection.*;
import com.gitbaby.board.repository.search.SearchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {
  @Query("select b board, w as member from Board b left join b.writer w where b.bno = :bno")
  BoardWithWriterDTO getBoardWithWriter(@Param("bno") Long bno);

  @Query("select b board, w as member from Board b left join b.writer w where b.bno = :bno")
  BoardWithWriterDTORecode getBoardWithWriter2(@Param("bno") Long bno);

  @Query("select b board, w as member from Board b left join b.writer w where b.bno = :bno")
  BoardWithWriterDTOClass getBoardWithWriter3(@Param("bno") Long bno);

  @Query("select b board, r reply from Board b left join Reply r on r.board = b where b.bno = :bno")
  List<BoardWithReplyDTO> getBoardWithReply(@Param("bno") Long bno);

  @Query(value = "select b board, w as member, count(r) as count from Board b left join b.writer w " +
          "left join Reply r on r.board = b group by b",
          countQuery = "select count(b) from Board b")
  Page<BoardWithReplyCountDTO> getBoardWithReplyCount(Pageable pageable);


  @Query(value = "select b board, w as member, count(r) as count from Board b left join b.writer w " +
          "left join Reply r on r.board = b where b.bno = :bno",
          countQuery = "select count(b) from Board b")
  BoardWithReplyCountDTO getBoardByBno(Long bno);
}
