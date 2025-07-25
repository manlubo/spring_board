package com.gitbaby.board.projection.dto;

import com.gitbaby.board.entity.Board;
import com.gitbaby.board.entity.Member;
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
