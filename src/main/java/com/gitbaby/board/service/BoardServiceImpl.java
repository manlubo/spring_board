package com.gitbaby.board.service;

import com.gitbaby.board.dto.PageRequestDTO;
import com.gitbaby.board.dto.PageResponseDTO;
import com.gitbaby.board.dto.BoardDTO;
import com.gitbaby.board.entity.Board;
import com.gitbaby.board.projection.dto.BoardWithReplyCountDTO;
import com.gitbaby.board.repository.BoardRepository;
import com.gitbaby.board.repository.ReplyRepository;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Data
public class BoardServiceImpl implements  BoardService {
  private final BoardRepository boardRepository;
  private final ReplyRepository replyRepository;

  @Override
  public Long register(BoardDTO boardDTO) {
    return boardRepository.save(toEntity(boardDTO)).getBno();
  }

  @Override
  public PageResponseDTO<BoardDTO, BoardWithReplyCountDTO> getList(PageRequestDTO pageRequestDTO) {
    Function<BoardWithReplyCountDTO, BoardDTO> mapper = bwrc -> toDTO(bwrc.board(), bwrc.member(), bwrc.count());
    return new PageResponseDTO<>(boardRepository.getBoardWithReplyCount(PageRequest.of(pageRequestDTO.getPage() - 1, 10, Sort.by(Sort.Direction.DESC,"bno")))
            , mapper);
  }

  @Override
  public BoardDTO get(Long bno) {
    return projectionToDTO(boardRepository.getBoardByBno(bno));
  }

  @Override
  public void remove(Long bno) {
    replyRepository.deleteByBoard_bno(bno);
    boardRepository.deleteById(bno);
  }

  @Override
  public void modify(BoardDTO boardDTO) {
    Board board = boardRepository.findById(boardDTO.getBno()).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
    board.changeTitle(boardDTO.getTitle()); // 1 transaction
    board.changeContent(boardDTO.getContent()); // 1 transaction
    boardRepository.save(board);
  }
}
