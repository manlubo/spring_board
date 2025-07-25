package com.gitbaby.board.repository;

import com.gitbaby.board.entity.Board;
import com.gitbaby.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
  void deleteByBoard_bno(Long bno);
}
