package com.gitbaby.board.repository;

import com.gitbaby.board.domain.entity.Board;
import com.gitbaby.board.domain.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
  void deleteByBoard_bno(Long bno);

  List<Reply> findByBoard_bnoOrderByRnoDesc(Long bno);

  List<Reply> findByBoard(Board board);
}
