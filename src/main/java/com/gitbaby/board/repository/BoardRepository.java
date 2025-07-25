package com.gitbaby.board.repository;

import com.gitbaby.board.projection.dto.BoardWithReplyDTO;
import com.gitbaby.board.projection.dto.BoardWithWriterDTO;
import com.gitbaby.board.projection.dto.BoardWithWriterDTOClass;
import com.gitbaby.board.projection.dto.BoardWithWriterDTORecode;
import com.gitbaby.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
  @Query("select b board, w as member from Board b left join b.writer w where b.bno = :bno")
  BoardWithWriterDTO getBoardWithWriter(@Param("bno") Long bno);

  @Query("select b board, w as member from Board b left join b.writer w where b.bno = :bno")
  BoardWithWriterDTORecode getBoardWithWriter2(@Param("bno") Long bno);

  @Query("select b board, w as member from Board b left join b.writer w where b.bno = :bno")
  BoardWithWriterDTOClass getBoardWithWriter3(@Param("bno") Long bno);

  @Query("select b board, r reply from Board b left join Reply r on r.board = b where b.bno = :bno")
  List<BoardWithReplyDTO> getBoardWithReply(@Param("bno") Long bno);

}
