package com.gitbaby.board.domain.projection;

import com.gitbaby.board.domain.entity.Board;
import com.gitbaby.board.domain.entity.Member;
import lombok.Getter;

@Getter
public class BoardWithWriterDTOClass {
  private Board board;
  private Member member;

  public BoardWithWriterDTOClass(Board board, Member member) {
    this.board = board;
    this.member = member;
  }
}
