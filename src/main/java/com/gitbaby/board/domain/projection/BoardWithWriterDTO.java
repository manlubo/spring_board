package com.gitbaby.board.domain.projection;

import com.gitbaby.board.domain.entity.Board;
import com.gitbaby.board.domain.entity.Member;

public interface BoardWithWriterDTO {
  Board getBoard();
  Member getMember();
}
