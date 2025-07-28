package com.gitbaby.board.repository.search;

import com.gitbaby.board.domain.entity.Board;
import com.gitbaby.board.domain.projection.BoardWithReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository extends SearchSupport<Board>{
  Board search1();

  Page<BoardWithReplyCountDTO> searchPage(String type, String keyword, Pageable pageable);
}
