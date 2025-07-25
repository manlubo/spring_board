package com.gitbaby.board.service;

import com.gitbaby.board.dto.PageRequestDTO;
import com.gitbaby.board.dto.PageResponseDTO;
import com.gitbaby.board.entity.Board;
import com.gitbaby.board.entity.Member;
import com.gitbaby.board.dto.BoardDTO;
import com.gitbaby.board.projection.dto.BoardWithReplyCountDTO;

public interface BoardService {
  Long register(BoardDTO boardDTO);

  BoardDTO get(Long bno);
  PageResponseDTO<BoardDTO, BoardWithReplyCountDTO> getList(PageRequestDTO pageRequestDTO);

  default Board toEntity(BoardDTO dto) {
    return Board.builder()
            .bno(dto.getBno())
            .title(dto.getTitle())
            .content(dto.getContent())
            .writer(Member.builder().email(dto.getWriterEmail()).build())
            .build();
  }

  default BoardDTO toDTO(Board entity, Member member, Long replyCount) {
    return BoardDTO.builder()
            .bno(entity.getBno())
            .title(entity.getTitle())
            .content(entity.getContent())
            .regDate(entity.getRegDate())
            .modDate(entity.getModDate())
            .writerEmail(member.getEmail())
            .writerName(member.getName())
            .replyCount(replyCount)
            .build();
  }

  default BoardDTO projectionToDTO(BoardWithReplyCountDTO entity) {
    return BoardDTO.builder()
            .bno(entity.board().getBno())
            .title(entity.board().getTitle())
            .content(entity.board().getContent())
            .regDate(entity.board().getRegDate())
            .modDate(entity.board().getModDate())
            .writerEmail(entity.member().getEmail())
            .writerName(entity.member().getName())
            .replyCount(entity.count())
            .build();
  }

  void remove(Long bno);


  void modify(BoardDTO boardDTO);
}
